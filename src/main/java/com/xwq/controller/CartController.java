package com.xwq.controller;

import com.xwq.entity.Cart;
import com.xwq.service.CartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Cart)表控制层
 *
 * @author makejava
 * @since 2020-06-15 14:53:42
 */
@RestController
@RequestMapping("cart")
public class CartController {
    /**
     * 服务对象
     */
    @Resource
    private CartService cartService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Cart selectOne(Integer id) {
        return this.cartService.queryById(id);
    }

}