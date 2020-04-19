package org.zyx.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.zyx.entity.Question;

/**
 * Created by SunShine on 2020/4/19.
 */
@Repository
@Mapper
public interface QuestionRepository {

    @Insert("insert into question(title,discription,gmt_create,gmt_modified,creater_id,tags) " +
            "values(#{title},#{discription},#{gmt_create},#{gmt_modified},#{creater_id},#{tags})")
    int sendQuestion(Question question);



}
