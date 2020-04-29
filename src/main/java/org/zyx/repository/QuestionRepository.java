package org.zyx.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.zyx.entity.Question;

import java.util.List;

/**
 * Created by SunShine on 2020/4/19.
 */
@Repository
@Mapper
public interface QuestionRepository {

    @Insert("insert into question(title,discription,gmt_create,gmt_modified,creater_id,tags) " +
            "values(#{title},#{discription},#{gmt_create},#{gmt_modified},#{creater_id},#{tags})")
    int sendQuestion(Question question);

    //当传递的参数不是对象是,我们需要使用@param映射参数
    @Select("select * from question limit #{currentPage} , #{count}")
    List<Question> findQuestion(@Param(value="currentPage") Integer currentPage, @Param(value="count")Integer count);

    @Select("select count(1) from question")
    Integer findCount();

    @Select("select * from question where creater_id=#{param1} limit #{param2} , #{param3}")
    List<Question> MyQuestions(Long id, Integer currentPage, Integer count);

    @Select("select count(1) from question where id=#{user_id}")
    Integer findCountByUid(Long user_id);

    @Select("select * from question where id=#{id}")
    Question findById(int id);
}
