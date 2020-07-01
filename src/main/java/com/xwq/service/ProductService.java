package com.xwq.service;

import com.xwq.entity.Product;
import com.xwq.util.ErrorMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Product)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
public interface ProductService {

    //1.单条查询-id
    public Product findOne(int id);

    //2.查询所有商品
    public List<Product> findAll();

    //3.增加
    public void add(Product product);

    //4.更新
    public void upd(Product product);

    //5.删除
    public void del(int id);

    //6.通过产品类别id查询
    public List<Product> findProductByCid(int cid);

    //7.批量删除
    public ErrorMsg deleteList(String ids);

    //8模糊查询
    public List<Product> likeQuerry(@Param("name") String name);
}
