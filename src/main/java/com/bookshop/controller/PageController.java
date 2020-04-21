package com.bookshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PageController
 * @Description: 控制页面跳转 相当于前端服务器
 * @Author: 曾志昊
 * @Date: 2020/4/3 19:50
 */

@Controller
@RequestMapping("/jump")
public class PageController {

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "/pages/register.html";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/pages/login.html";
    }

}
