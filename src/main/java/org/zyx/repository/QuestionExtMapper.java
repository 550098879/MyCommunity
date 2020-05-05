package org.zyx.repository;

import org.springframework.stereotype.Repository;

/**
 * 拓展接口
 */
@Repository
public interface QuestionExtMapper {

    int incView(Long id);
    int incCommentCount(Long id);

}
