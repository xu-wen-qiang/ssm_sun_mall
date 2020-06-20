package com.xwq.service;

import com.xwq.entity.OrderItem;

import java.util.List;

/**
 * (OrderItem)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
public interface OrderItemService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderItem queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OrderItem> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param orderItem 实例对象
     * @return 实例对象
     */
    OrderItem insert(OrderItem orderItem);

    /**
     * 修改数据
     *
     * @param orderItem 实例对象
     * @return 实例对象
     */
    OrderItem update(OrderItem orderItem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}