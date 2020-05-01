package org.zyx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyx.entity.PagingData;
import org.zyx.entity.Question;
import org.zyx.repository.QuestionRepository;
import org.zyx.repository.UserRepository;
import org.zyx.service.QuestionService;

import java.util.List;

/**
 * Created by SunShine on 2020/4/19.
 */
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PagingData findQuestion(Integer currentPage, Integer count) {

        PagingData pagingData = new PagingData();
        List<Question> questionList=questionRepository.findQuestion((currentPage-1)*count,count);
        for (Question question: questionList) {
            Long creater_id=question.getCreater_id();
            question.setUser(userRepository.findById(creater_id));
        }
        pagingData.setQuestionList(questionList);//设置问题列表

        Integer totalCount=questionRepository.findCount();

        pagingData.setPagination(totalCount,currentPage,count);

        return pagingData;
    }

    @Override
    public PagingData findMyQuestion(Long user_id, Integer currentPage, Integer count) {

        PagingData pagingData = new PagingData();

        List<Question> questionList=questionRepository.MyQuestions(user_id,(currentPage-1)*count,count);
        for (Question question: questionList) {
            Long creater_id=question.getCreater_id();
            question.setUser(userRepository.findById(creater_id));
        }
        pagingData.setQuestionList(questionList);//设置问题列表

        Integer totalCount=questionRepository.findCountByUid(user_id);

        pagingData.setPagination(totalCount,currentPage,count);

        return pagingData;

    }

    @Override
    public Question getById(int id) {
        Question question = questionRepository.findById(id);
        question.setUser(userRepository.findById(question.getCreater_id()));
        return question;
    }

    @Override
    public int createOrUpdate(Question question) {

        int count=0;

        if (question.getId() == null){
            //创建
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(System.currentTimeMillis());
            count = questionRepository.sendQuestion(question);
        }else{
            //更新
            question.setGmt_modified(System.currentTimeMillis());
            questionRepository.updateById(question);
        }

        return count;
    }


}
