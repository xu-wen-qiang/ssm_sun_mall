package com.xwq.controller;

import com.xwq.entity.PayInfo;
import com.xwq.service.PayInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (PayInfo)表控制层
 *
 * @author makejava
 * @since 2020-06-15 14:50:18
 */
@RestController
@RequestMapping("payInfo")
public class PayInfoController {
    /**
     * 服务对象
     */
    @Resource
    private PayInfoService payInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public PayInfo selectOne(Integer id) {
        return this.payInfoService.queryById(id);
    }

}