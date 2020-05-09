package org.zyx.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zyx.entity.QuestionDTO;
import org.zyx.model.Question;

import java.util.Collection;

/**
 * 拓展接口
 */
@Repository
public interface QuestionExtMapper {

    int incView(Long id);
    int incCommentCount(Long id);
    Collection<Question> aboutQuestion(@Param("id") Long id ,@Param("tags") String tags);

}
