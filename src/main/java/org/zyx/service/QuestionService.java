package org.zyx.service;

import org.springframework.stereotype.Service;
import org.zyx.entity.Question;

import java.util.List;

/**
 * Created by SunShine on 2020/4/19.
 */
public interface QuestionService {

    List<Question> findQuestion(Integer currentPage,Integer count);

}
