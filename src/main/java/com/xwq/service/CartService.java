package com.xwq.service;

import com.xwq.entity.Cart;
import com.xwq.util.ErrorMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Cart)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 14:53:42
 */
public interface CartService {

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
    Cart queryById(Integer pid, Integer uid);

    /**
     *
     * @param ids
     * @param uid
     * @return
     */
    List<Cart> queryList(@Param("ids") String ids, @Param("uid") Integer uid);
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
    ErrorMsg deleteList(String ids, Integer uid);
}