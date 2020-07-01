package com.xwq.service.impl;

import com.xwq.entity.Cart;
import com.xwq.entity.Product;
import com.xwq.dao.ProductDao;
import com.xwq.service.ProductService;
import com.xwq.util.ErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Product)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    public Product findOne(int id) {
        return productDao.findOne(id);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public void add(Product product) {
        productDao.add(product);
    }

    public void upd(Product product) {
        productDao.upd(product);
    }

    public void del(int id) {
        productDao.del(id);
    }

    public List<Product> findProductByCid(int cid) {
        return productDao.findProductByCid(cid);
    }

    @Override
    public ErrorMsg deleteList(String ids) {
        Integer id = null;
        String[] idStrings = ids.split(",");
        //dao层接受的全部id数据,pIdList
        int[] pIdList = new int[idStrings.length];
        for (int i = 0; i < idStrings.length; i++) {
            id = Integer.valueOf(idStrings[i]);
            //得到一个id
            pIdList[i] = id;
            productDao.deleteList(pIdList);
            //查询传入的最后一个id,如果为空,说明删除成功,返回ok;如果不为空,说明删除失败,返回任意字符串;
            Product product = productDao.findOne(id);
            if (product != null) {
                return ErrorMsg.DELETE_ERROR;
            }
        }
        return ErrorMsg.DELETE_SUCCESS;
    }

    @Override
    public List<Product> likeQuerry(String name) {
        return productDao.likeQuerry(name);
    }

}