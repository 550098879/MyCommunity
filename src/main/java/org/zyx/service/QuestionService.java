package org.zyx.service;

import org.zyx.entity.PagingData;
import org.zyx.entity.QuestionModel;

/**
 * Created by SunShine on 2020/4/19.
 */
public interface QuestionService {

    PagingData findQuestion(Integer currentPage, Integer count);

    PagingData findMyQuestion(int id, Integer currentPage, Integer count);

    QuestionModel getById(int id);

    int createOrUpdate(QuestionModel question);
}
