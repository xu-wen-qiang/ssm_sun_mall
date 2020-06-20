package com.xwq.service;

import com.xwq.entity.Shipping;

import java.util.List;

/**
 * (Shipping)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
public interface ShippingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Shipping queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Shipping> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param shipping 实例对象
     * @return 实例对象
     */
    Shipping insert(Shipping shipping);

    /**
     * 修改数据
     *
     * @param shipping 实例对象
     * @return 实例对象
     */
    Shipping update(Shipping shipping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}