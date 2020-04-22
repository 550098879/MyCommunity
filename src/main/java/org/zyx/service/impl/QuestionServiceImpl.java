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


}
