package org.zyx.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyx.entity.PagingData;
import org.zyx.entity.QuestionDTO;
import org.zyx.exception.CustomizeErrorCode;
import org.zyx.exception.CustomizeException;
import org.zyx.model.Question;
import org.zyx.model.QuestionExample;
import org.zyx.repository.QuestionExtMapper;
import org.zyx.repository.QuestionMapper;
import org.zyx.repository.UserMapper;
import org.zyx.service.QuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by SunShine on 2020/4/19.
 */
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionMapper questionMapper ;
    @Autowired
    private UserMapper userMapper ;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Override
    public PagingData findQuestion(String search,Integer currentPage, Integer count) {

        PagingData<QuestionDTO> pagingData = new PagingData();
        List<QuestionDTO> questionDTOS =new ArrayList<>();
        QuestionExample example = new QuestionExample();
        example.setOrderByClause("gmt_modified desc");
        if(search.length() > 0){
            example.createCriteria().andTitleLike("%"+search+"%");
        }
        List<Question> questionList=questionMapper
                .selectByExampleWithBLOBsWithRowbounds(example,new RowBounds((currentPage-1)*count,count));
        for (Question question: questionList) {
            long creater_id=question.getCreaterId();
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//属性复制
            questionDTO.setUser(userMapper.selectByPrimaryKey(creater_id));
            questionDTOS.add(questionDTO);
        }
        pagingData.setData(questionDTOS);//设置问题列表
        Integer totalCount=(int)questionMapper.countByExample(example);
        pagingData.setPagination(totalCount,currentPage,count);

        return pagingData;
    }

    @Override
    public PagingData findMyQuestion(long user_id, Integer currentPage, Integer count) {

        PagingData<QuestionDTO>  pagingData = new PagingData();
        List<QuestionDTO> questionDTOS =new ArrayList<>();
        QuestionExample example1 = new QuestionExample();
        example1.createCriteria().andCreaterIdEqualTo(user_id);
        List<Question> questionList=questionMapper
                .selectByExampleWithBLOBsWithRowbounds(example1,new RowBounds((currentPage-1)*count,count));

        for (Question question : questionList) {
            long creater_id=question.getCreaterId();
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//属性复制
            questionDTO.setUser(userMapper.selectByPrimaryKey(creater_id));
            questionDTOS.add(questionDTO);
        }
        pagingData.setData(questionDTOS);//设置问题列表

        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreaterIdEqualTo((long) user_id);
        Integer totalCount=(int)questionMapper.countByExample(example);

        pagingData.setPagination(totalCount,currentPage,count);

        return pagingData;

    }

    @Override
    public QuestionDTO getById(long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);//属性复制
        questionDTO.setUser(userMapper.selectByPrimaryKey(question.getCreaterId()));
        return questionDTO;
    }

    @Override
    public int createOrUpdate(QuestionDTO questionDTO) {

        int count=0;

        if (questionDTO.getId() == null){
            //创建,参数是Question的对象,而不是QuestionModel(虽然也可以就是了,子类)
            Question addPro=new Question();
            addPro.setCreaterId(questionDTO.getCreaterId());
            addPro.setGmtCreate(System.currentTimeMillis());
            addPro.setGmtModified(System.currentTimeMillis());
            addPro.setTitle(questionDTO.getTitle());
            addPro.setDiscription(questionDTO.getDiscription());
            addPro.setTags(questionDTO.getTags());
            count = questionMapper.insertSelective(addPro);

        }else{
            //更新
            Question update = new Question();
            update.setGmtModified(System.currentTimeMillis());
            update.setTitle(questionDTO.getTitle());
            update.setDiscription(questionDTO.getDiscription());
            update.setTags(questionDTO.getTags());

            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(questionDTO.getId());
            if(questionMapper.updateByExampleSelective(update,example) != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }

        return count;
    }

    @Override
    public Collection<Question> selectRelated(QuestionDTO questionDTO) {

        if(StringUtils.isBlank(questionDTO.getTags())){
            return new ArrayList<>();
        }
        String tags=questionDTO.getTags().replace(",","|");

        return questionExtMapper.aboutQuestion(questionDTO.getId(),tags);
    }


}
