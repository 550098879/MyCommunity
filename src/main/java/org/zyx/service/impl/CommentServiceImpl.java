package org.zyx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zyx.entity.CommentDTO;
import org.zyx.entity.CommentData;
import org.zyx.enums.CommentTypeEnum;
import org.zyx.exception.CustomizeErrorCode;
import org.zyx.exception.CustomizeException;
import org.zyx.model.Comment;
import org.zyx.model.CommentExample;
import org.zyx.model.Question;
import org.zyx.model.User;
import org.zyx.repository.CommentMapper;
import org.zyx.repository.QuestionExtMapper;
import org.zyx.repository.QuestionMapper;
import org.zyx.repository.UserMapper;
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


    /**
     * @param commentDTO 接收前端传来的回复参数
     * @param userId     回复用户ID
     * @Transactional 将整个方法作为事务, 失败则整体回滚
     */
    @Override
    @Transactional //事务功能
    public void insertComment(CommentDTO commentDTO, Long userId) {
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
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(commentDTO.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentId(userId);

        commentMapper.insertSelective(comment);
        questionExtMapper.incCommentCount(commentDTO.getParentId());   //增加评论数

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
}
