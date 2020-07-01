package com.xwq.dao;

import com.xwq.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Product)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
public interface ProductDao {
    //1.单条查询-id
    public Product findOne(int id);

    //2.查询所有商品
    public List<Product> findAll();

    //3.增加
    public void add(Product items);

    //4.更新
    public void upd(Product items);

    //5.删除
    public void del(int id);

    //6通过种类id查询对应商品信息
    public List<Product> findProductByCid(int cid);

    //7批量删除
    public void deleteList(@Param("ids") int[] ids);

    public List<Product> likeQuerry(@Param("name") String name);
}