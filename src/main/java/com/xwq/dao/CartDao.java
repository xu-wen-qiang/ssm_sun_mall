package com.xwq.dao;

import com.xwq.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Cart)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 14:53:42
 */
public interface CartDao {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param cart 实例对象
     * @return 对象列表
     */
    List<Cart> queryAll(Cart cart);

    /**
     * 通过ID查询单条数据
     *
     * @param cart 实例对象
     * @return 实例对象
     */
    Cart queryOne(Cart cart);
    /**
     * 通过ID查询单条数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    Cart queryOneById(Integer id);
    /**
     * 通过ID查询单条数据
     *
     * @param pid 主键
     * @return 实例对象
     */
    Cart queryById(@Param("pid") Integer pid, @Param("uid") Integer uid);

    /**
     *
     * @param ids
     * @param uid
     * @return
     */
    List<Cart> queryList(@Param("ids") int[] ids, @Param("uid") int uid);
    /**
     * 新增数据
     *
     * @param cart 实例对象
     * @return 影响行数
     */
    int insert(Cart cart);

    /**
     * 修改数据
     *
     * @param cart 实例对象
     * @return 影响行数
     */
    int update(Cart cart);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * @param ids 用户
     * @param uid 产品
     * @return
     */
    int deleteList(@Param("ids") int[] ids, @Param("uid") int uid);
}