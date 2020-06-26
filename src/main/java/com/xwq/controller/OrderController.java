package com.xwq.controller;

import com.xwq.entity.Order;
import com.xwq.entity.User;
import com.xwq.service.OrderService;
import com.xwq.util.ErrorMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * (Order)表控制层
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
@Controller
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @RequestMapping("queryById")
    @ResponseBody
    ErrorMsg queryById(Integer id){
        return ErrorMsg.SUCCESS.setNewData(orderService.queryById(id));
    };

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @RequestMapping("queryAllByLimit")
    @ResponseBody
    ErrorMsg queryAllByLimit(int offset, int limit){
        return ErrorMsg.SUCCESS.setNewData(orderService.queryAllByLimit(offset,limit));
    };
    /**
     * 通过实体作为筛选条件查询
     *
     * @param order 实例对象
     * @return 对象列表
     */
    @RequestMapping("queryAll")
    @ResponseBody
    ErrorMsg queryAll(Order order){
        return ErrorMsg.SUCCESS.setNewData(orderService.queryAll(order));
    };
    /**
     * 新增数据
     *
     * @param
     * @return 实例对象
     */
    @RequestMapping("insert")
    @ResponseBody
    ErrorMsg insert(String cartIds , String address , HttpSession session){

        return ErrorMsg.INSERT_SUCCESS.setNewData(orderService.insertList(cartIds,address));
    };

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @RequestMapping("update")
    @ResponseBody
    ErrorMsg update(Order order){
        return ErrorMsg.UPDATE_SUCCESS.setNewData(orderService.update(order));
    };

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @RequestMapping("deleteById")
    @ResponseBody
    ErrorMsg deleteById(Integer id){
        return ErrorMsg.DELETE_SUCCESS.setNewData(orderService.deleteById(id));
    };

}