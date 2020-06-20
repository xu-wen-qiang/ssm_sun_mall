package com.xwq.service;

import com.xwq.entity.Category;

import java.util.List;

/**
 * (Category)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 14:50:17
 */
public interface CategoryService {
    /**
     * @return
     */
    public List<Category> listCategories();
}