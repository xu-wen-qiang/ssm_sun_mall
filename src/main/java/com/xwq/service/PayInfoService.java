package com.xwq.service;

import com.xwq.entity.PayInfo;

import java.util.List;

/**
 * (PayInfo)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
public interface PayInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PayInfo queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PayInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param payInfo 实例对象
     * @return 实例对象
     */
    PayInfo insert(PayInfo payInfo);

    /**
     * 修改数据
     *
     * @param payInfo 实例对象
     * @return 实例对象
     */
    PayInfo update(PayInfo payInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}