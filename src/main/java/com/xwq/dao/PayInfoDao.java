package com.xwq.dao;

import com.xwq.entity.PayInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (PayInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
public interface PayInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PayInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PayInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param payInfo 实例对象
     * @return 对象列表
     */
    List<PayInfo> queryAll(PayInfo payInfo);

    /**
     * 新增数据
     *
     * @param payInfo 实例对象
     * @return 影响行数
     */
    int insert(PayInfo payInfo);

    /**
     * 修改数据
     *
     * @param payInfo 实例对象
     * @return 影响行数
     */
    int update(PayInfo payInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}