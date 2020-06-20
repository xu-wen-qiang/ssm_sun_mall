package com.xwq.service;

import com.xwq.entity.User;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 14:52:01
 */
public interface UserService {
    /**
     * 登陆
     *
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 查询是否有同名对象
     *
     * @param user
     * @return
     */
    public int findOne(User user);

    /**
     * 注册
     *
     * @param user
     */
    public int register(User user);
}