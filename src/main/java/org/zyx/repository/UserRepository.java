package org.zyx.repository;



import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.zyx.entity.User;

import java.util.List;

/**
 * Created by SunShine on 2020/4/17.
 */
@Repository
@Mapper  //直接声明这是一个mapper类映射,减去mapping的配置?
public interface UserRepository {

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id=#{id}")
    User findById(Long id);

    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,bio,avatar_url) " +
            "values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified},#{bio},#{avatar_url})")
    void insert(User user);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmt_modified},avatar_url=#{avatar_url}" +
            "where id=#{id}")
    void update(User user);


    void deleteById(Integer id);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where account_id=#{account_id}")
    User findByAccountId(String account_id);
}
