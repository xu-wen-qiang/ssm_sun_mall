package com.xwq.controller;

import com.xwq.entity.Category;
import com.xwq.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Category)表控制层
 *
 * @author makejava
 * @since 2020-06-15 14:50:17
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/listCategories")
    private void listCategories() {
        List<Category> categories = categoryService.listCategories();
        for (Category category : categories) {
            System.out.println("========================================" + category.toString());
        }
//      return categories.toString();
    }

    @RequestMapping(value = "/clist")
    public @ResponseBody
    List<Category> listCategoriesByJson() {
        List<Category> categoriesList = categoryService.listCategories();
        for (Category category : categoriesList) {
            System.out.println("========================================" + category.toString());
        }
        return categoriesList;
    }
}