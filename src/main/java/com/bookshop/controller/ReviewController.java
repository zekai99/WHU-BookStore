package com.bookshop.controller;

import com.bookshop.common.checkSession;
import com.bookshop.common.responseFromServer;
import com.bookshop.entity.Order;
import com.bookshop.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName: ReviewController
 * @Description: TODO
 * @Author: 曾志昊
 * @Date: 2020/4/12 16:01
 */
//@RequestMapping("/review")
public class ReviewController {


//    @RequestMapping("/insertReview")
//    @ResponseBody
//    public responseFromServer insertReview(@RequestBody Map<String,Object> map, HttpSession session){
//        Integer orderId = null;
//        if(map.get("orderId")==null){
//            return responseFromServer.error("请求错误");
//        }else{
//            orderId = (Integer)map.get("orderId");
//        }
//        if(checkSession.checkManager(session)){
//
//        }else if(checkSession.check(session)){
//            User user = (User)session.getAttribute("user");
//
//        }
//
//        return responseFromServer.success();
//
//    }

}
