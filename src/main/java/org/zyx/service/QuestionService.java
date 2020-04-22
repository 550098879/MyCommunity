package org.zyx.service;

import org.zyx.entity.PagingData;

/**
 * Created by SunShine on 2020/4/19.
 */
public interface QuestionService {

    PagingData findQuestion(Integer currentPage, Integer count);

}
