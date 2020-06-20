package com.xwq.service.impl;

import com.xwq.entity.Product;
import com.xwq.dao.ProductDao;
import com.xwq.service.ProductService;
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
}