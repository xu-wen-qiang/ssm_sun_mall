package com.xwq.controller;

import com.xwq.entity.Shipping;
import com.xwq.service.ShippingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Shipping)表控制层
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
@RestController
@RequestMapping("shipping")
public class ShippingController {
    /**
     * 服务对象
     */
    @Resource
    private ShippingService shippingService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Shipping selectOne(Integer id) {
        return this.shippingService.queryById(id);
    }

}