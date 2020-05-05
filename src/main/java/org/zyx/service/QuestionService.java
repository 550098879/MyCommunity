package org.zyx.service;

import org.zyx.entity.PagingData;
import org.zyx.entity.QuestionDTO;

/**
 * Created by SunShine on 2020/4/19.
 */
public interface QuestionService {

    PagingData findQuestion(Integer currentPage, Integer count);

    PagingData findMyQuestion(long id, Integer currentPage, Integer count);

    QuestionDTO getById(long id);

    int createOrUpdate(QuestionDTO question);

}
