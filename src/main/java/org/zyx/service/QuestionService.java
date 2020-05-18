package org.zyx.service;

import org.zyx.entity.PagingData;
import org.zyx.entity.QuestionDTO;
import org.zyx.model.Question;

import java.util.Collection;

/**
 * Created by SunShine on 2020/4/19.
 */
public interface QuestionService {

    PagingData findQuestion(String search,Integer currentPage, Integer count);

    PagingData findMyQuestion(long id, Integer currentPage, Integer count);

    QuestionDTO getById(long id);

    int createOrUpdate(QuestionDTO question);

    Collection<Question> selectRelated(QuestionDTO questionDTO);
}
