package org.zyx.repository;



import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    void update(User user);
    void deleteById(Integer id);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);



}
