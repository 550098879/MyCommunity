package org.zyx.repository;

import org.springframework.stereotype.Repository;

/**
 * 拓展接口
 */
@Repository
public interface QuestionExtMapper {

    void incView(int id);

}
