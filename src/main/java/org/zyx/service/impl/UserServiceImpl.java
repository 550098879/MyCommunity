package org.zyx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyx.model.User;
import org.zyx.model.UserExample;
import org.zyx.repository.UserMapper;
import org.zyx.service.UserService;

import java.util.List;

/**
 * Created by SunShine on 2020/4/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper ;

    @Override
    public void createOrUpdate(User user) {

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.size() == 0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertSelective(user);
        }else{
            //修改

            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());


            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(userList.get(0).getId());
            userMapper.updateByExampleSelective(updateUser, example);
        }


    }

}
