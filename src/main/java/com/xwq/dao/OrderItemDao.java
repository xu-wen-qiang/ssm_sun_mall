package com.xwq.dao;

import com.xwq.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (OrderItem)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
public interface OrderItemDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderItem queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OrderItem> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orderItem 实例对象
     * @return 对象列表
     */
    List<OrderItem> queryAll(OrderItem orderItem);

    /**
     * 新增数据
     *
     * @param orderItem 实例对象
     * @return 影响行数
     */
    int insert(OrderItem orderItem);

    /**
     * 修改数据
     *
     * @param orderItem 实例对象
     * @return 影响行数
     */
    int update(OrderItem orderItem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}