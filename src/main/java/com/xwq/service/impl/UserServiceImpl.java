package com.xwq.service.impl;

import com.xwq.dao.UserDao;
import com.xwq.entity.User;
import com.xwq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 14:52:01
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public User login(User user) {
        return userDao.Login(user);
    }

    public int findOne(User user) {
        return userDao.findOne(user);
    }

    public int register(User user) {
        int userId = userDao.Register(user);
        if (userId != 0) {
            userId = user.getId();
        }
        return userId;
    }

    public int update(User user) {
        return userDao.update(user);
    }
}