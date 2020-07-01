package com.xwq.dao;

import com.xwq.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 14:52:01
 */
@Transactional
public interface UserDao {
    /**
     * 登陆
     *
     * @param user
     * @return
     */
    public User Login(User user);

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
    public int Register(User user);

    /**
     * 修改
     * @param user
     * @return
     */
    public int update(User user);
}