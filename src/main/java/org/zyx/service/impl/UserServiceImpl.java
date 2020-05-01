package org.zyx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyx.entity.User;
import org.zyx.repository.UserRepository;
import org.zyx.service.UserService;

/**
 * Created by SunShine on 2020/4/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createOrUpdate(User user) {

        User dbUser = userRepository.findByAccountId(user.getAccount_id());

        if(dbUser == null){
            //插入
            user.setGmt_create(System.currentTimeMillis());//创建时间
            user.setGmt_modified(user.getGmt_create());
            userRepository.insert(user);
        }else{
//            更新
            dbUser.setGmt_modified(System.currentTimeMillis());//资料修改时间
            dbUser.setAvatar_url(user.getAvatar_url());//更新头像
            dbUser.setToken(user.getToken());//更新token
            dbUser.setName(user.getName());
            userRepository.update(dbUser);
        }

    }
}
