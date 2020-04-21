package com.bookshop.interceptor;

import com.bookshop.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    /*拦截器的实现暂时先不在一开始写，先全部放行，之后再在业务逻辑中编写该拦截器的具体逻辑*/

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User u = (User) httpServletRequest.getSession().getAttribute("user");
        if(u!=null) {
            return true;
        }else {
            //为空返回到登录页面
            httpServletResponse.sendRedirect("/index");
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
