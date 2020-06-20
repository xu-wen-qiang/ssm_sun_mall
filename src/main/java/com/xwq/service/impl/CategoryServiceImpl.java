package com.xwq.service.impl;

import com.xwq.dao.CategoryDao;
import com.xwq.entity.Category;
import com.xwq.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 14:50:17
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    /**
     * @return
     */
    public List<Category> listCategories() {
        return categoryDao.listCategories();
    }
}