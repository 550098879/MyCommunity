package org.zyx.service;

import org.zyx.entity.PagingData;
import org.zyx.entity.Question;

/**
 * Created by SunShine on 2020/4/19.
 */
public interface QuestionService {

    PagingData findQuestion(Integer currentPage, Integer count);

    PagingData findMyQuestion(Long id, Integer currentPage, Integer count);

    Question getById(int id);

    int createOrUpdate(Question question);
}
