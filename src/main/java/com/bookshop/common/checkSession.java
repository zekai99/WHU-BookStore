package com.bookshop.common;

import com.bookshop.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: checkSession
 * @Description: 权限检查，检查当前登录用户为普通用户还是管理员
 * @Author: 曾志昊
 * @Date: 2020/4/7 17:27
 */
public class checkSession {

    public static boolean check(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user==null||user.getUserId()==null){
            return false;
        }
        return true;
    }

    public static boolean checkManager(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user==null||user.getUserId()==null||!user.getUserStatus().equals("manager")){
            return false;
        }
        return true;
    }
}
