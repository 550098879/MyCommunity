package org.zyx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zyx.entity.CommentDTO;
import org.zyx.entity.CommentData;
import org.zyx.enums.CommentTypeEnum;
import org.zyx.enums.InformEnum;
import org.zyx.enums.InformStatusEnum;
import org.zyx.exception.CustomizeErrorCode;
import org.zyx.exception.CustomizeException;
import org.zyx.model.*;
import org.zyx.repository.*;
import org.zyx.service.CommentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by SunShine on 2020/5/4.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private InformMapper informMapper;


    /**
     * @param commentDTO 接收前端传来的回复参数
     * @Transactional 将整个方法作为事务, 失败则整体回滚
     */
    @Override
    @Transactional //事务功能
    public void insertComment(CommentDTO commentDTO, User user) {
        //判断问题是否被删除,或不存在
        if (commentDTO.getParentId() == null || commentDTO.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        //判断评论类型是否存在
        if (commentDTO.getType() == null || !CommentTypeEnum.isExist(commentDTO.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (commentDTO.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            //通过parentId获取回复,parentId就是评论ID?
            Comment dbcomment = commentMapper.selectByPrimaryKey(commentDTO.getParentId());
            if (dbcomment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentExtMapper.incCommentCount(commentDTO.getParentId());
            //创建通知
            createInform(dbcomment.getParentId(), user, dbcomment.getCommentId(), InformEnum.REPLY_COMMENT,dbcomment.getContent());

        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(commentDTO.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //创建通知
            createInform(question.getId(), user, question.getCreaterId(), InformEnum.REPLY_QUESTION,question.getTitle());
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentId(user.getId());

        commentMapper.insertSelective(comment);
        questionExtMapper.incCommentCount(commentDTO.getParentId());   //增加评论数

    }

    private void createInform(Long questionId, User user, Long receiver, InformEnum informEnum,String title) {
        /**添加通知
         *  1.ctrl + alt + m 将选中的代码生成方法
         *  2.ctrl + alt + p 将将变量抽出变为参数(形参)
         */
        Inform inform = new Inform();
        inform.setGmtCreate(System.currentTimeMillis());
        inform.setType(informEnum.getType());//类型
        inform.setOuterid(questionId);//问题ID
        inform.setNotifier(user.getId());//发出通知的人
        inform.setStatus(InformStatusEnum.UNREAD.getStatus());
        inform.setReceiver(receiver);//接受通知的人
        inform.setInformName(user.getName());//新增发出通知的人的用户名
        inform.setOuterTitle(title);//标题

        informMapper.insert(inform);
    }

    @Override
    public Collection<CommentData> findComment(long parentId, int type) {
        List<CommentData> commentDataList = new ArrayList<>();
        CommentExample example = new CommentExample();
        example.createCriteria().andParentIdEqualTo(parentId).andTypeEqualTo(type);
        example.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(example);
        for (Comment comment : comments) {
            commentDataList.add(new CommentData(comment,userMapper.selectByPrimaryKey(comment.getCommentId()),commentMapper.countByExample(example)));
        }
        return commentDataList;
    }

    @Override
    public Long incLikeCount(long commentId) {

        commentExtMapper.incLikeCount(commentId);

        Comment comment = commentMapper.selectByPrimaryKey(commentId);

        return comment.getLikeCount();
    }
}
