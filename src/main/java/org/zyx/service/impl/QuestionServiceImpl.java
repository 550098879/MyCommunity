package org.zyx.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyx.exception.CustomizeErrorCode;
import org.zyx.exception.CustomizeException;
import org.zyx.entity.PagingData;
import org.zyx.entity.QuestionModel;
import org.zyx.model.Question;
import org.zyx.model.QuestionExample;
import org.zyx.repository.QuestionMapper;
import org.zyx.repository.UserMapper;
import org.zyx.service.QuestionService;

import java.util.ArrayList;
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

    @Override
    public PagingData findQuestion(Integer currentPage, Integer count) {

        PagingData pagingData = new PagingData();
        List<QuestionModel> questionModels=new ArrayList<>();
        List<Question> questionList=questionMapper
                .selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(currentPage,count));

        for (Question question: questionList) {
            long creater_id=question.getCreaterId();
            QuestionModel questionModel = new QuestionModel();
            BeanUtils.copyProperties(question, questionModel);//属性复制
            questionModel.setUser(userMapper.selectByPrimaryKey((int)creater_id));
            questionModels.add(questionModel);
        }
        pagingData.setQuestionList(questionModels);//设置问题列表

        Integer totalCount=(int)questionMapper.countByExample(new QuestionExample());

        pagingData.setPagination(totalCount,currentPage,count);

        return pagingData;
    }

    @Override
    public PagingData findMyQuestion(int user_id, Integer currentPage, Integer count) {

        PagingData pagingData = new PagingData();
        List<QuestionModel> questionModels=new ArrayList<>();
        List<Question> questionList=questionMapper
                .selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(currentPage,count));

        for (Question question : questionList) {
            long creater_id=question.getCreaterId();
            QuestionModel questionModel = new QuestionModel();
            BeanUtils.copyProperties(question, questionModel);//属性复制
            System.out.println("");
            questionModel.setUser(userMapper.selectByPrimaryKey((int)creater_id));
            questionModels.add(questionModel);
        }
        pagingData.setQuestionList(questionModels);//设置问题列表

        QuestionExample example = new QuestionExample();
        example.createCriteria().andIdEqualTo((long)user_id);
        Integer totalCount=(int)questionMapper.countByExample(example);

        pagingData.setPagination(totalCount,currentPage,count);

        return pagingData;

    }

    @Override
    public QuestionModel getById(int id) {
        Question question = questionMapper.selectByPrimaryKey((long)id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionModel questionModel = new QuestionModel();
        BeanUtils.copyProperties(question, questionModel);//属性复制
        questionModel.setUser(userMapper.selectByPrimaryKey(Integer.parseInt(""+question.getCreaterId())));
        return questionModel;
    }

    @Override
    public int createOrUpdate(QuestionModel question) {

        int count=0;

        if (question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            count = questionMapper.insertSelective(question);
        }else{
            //更新
            Question update = new Question();
            update.setGmtModified(System.currentTimeMillis());
            update.setTitle(question.getTitle());
            update.setDiscription(question.getDiscription());
            update.setTags(question.getTags());

            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            if(questionMapper.updateByExampleSelective(update,example) != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }

        return count;
    }


}
