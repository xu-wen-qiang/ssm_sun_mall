package com.xwq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/pages")
public class PagesController {
    @RequestMapping(value = "/success")
    private String Success() {
        return "success";
    }

    @RequestMapping(value = "showItems")
    private String index() {
        return "showProduct";
    }
}
