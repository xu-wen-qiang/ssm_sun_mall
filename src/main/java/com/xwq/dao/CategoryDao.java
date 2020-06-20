package com.xwq.dao;

import com.xwq.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Category)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 14:50:15
 */
public interface CategoryDao {
    /**
     * @return
     */
    public List<Category> listCategories();
}