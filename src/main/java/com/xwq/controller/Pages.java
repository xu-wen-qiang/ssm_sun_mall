package com.xwq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/pages")
public class Pages {
    @RequestMapping(value = "/showProduct")
    public String showProduct() {
        return "showProduct";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

}
