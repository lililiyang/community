package com.huadao.community.service;

import com.huadao.community.mapper.UserMapper;
import com.huadao.community.model.User;
import com.huadao.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liyang
 * @date 2019/12/27
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size() == 0) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            User dbUser = new User();
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());

            UserExample example = new UserExample();
            example.createCriteria().andAccountIdEqualTo(user.getAccountId());
            userMapper.updateByExampleSelective(dbUser, example);
        }
    }
}
