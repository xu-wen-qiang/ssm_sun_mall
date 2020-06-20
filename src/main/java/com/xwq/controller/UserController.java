package com.xwq.controller;

import com.xwq.entity.User;
import com.xwq.service.impl.UserServiceImpl;
import com.xwq.util.ErrorMsg;
import com.xwq.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-06-15 14:52:01
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 时间转换
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @RequestMapping(value = "/login")
    public @ResponseBody
    ErrorMsg<User> Login(User user, HttpSession session, Model model) {
        /**
         *登陆验证
         * 判断用户名和密码是否正确
         *登录以后添加到session中
         * 返回json user对象
         */

        user.setPassword(Md5Util.getMD5(user.getPassword()));
        User User = userService.login(user);
        if (User != null) {
            return ErrorMsg.LOGIN_SUCCESS.setNewData(User);
        }

        return ErrorMsg.LOGIN_ERROR;
    }

    // 注销
    @RequestMapping("/logout")
    public String LogOut(HttpSession session) {
        /**
         * 清空session
         * 重定向到首页或登陆界面
         */
        session.invalidate();
        return "login";
    }

    // 注册
    @RequestMapping(value = "/register")
    public @ResponseBody
    ErrorMsg register(User user) {
        /**
         * 传递模态框、user对象
         * 判段用户是否存在
         * 调用userService注册方法
         * 返回成功或者失败
         */
        user.setPassword(Md5Util.getMD5(user.getPassword()));
        if (userService.register(user) != 0) {
            return ErrorMsg.INSERT_SUCCESS.setNewData(user);
        }
        return ErrorMsg.LOGIN_ERROR.setNewErrorMsg("用户已经存在");
    }

}