package com.bookshop.controller;

import com.bookshop.common.checkSession;
import com.bookshop.common.responseFromServer;
import com.bookshop.entity.CartItem;
import com.bookshop.entity.User;
import com.bookshop.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CartController
 * @Description:
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:48
 */

@Controller
@RequestMapping("/cart")
public class CartController {

    @Resource
    CartService cartService;

    /*@RequestMapping("/getCart")
    @ResponseBody
    public responseFromServer getCart(@RequestBody Map<String, Object> reqeustMap, HttpSession session) {
        if (checkSession.check(session)) {
            return cartService.getCart(reqeustMap);
        }
        return responseFromServer.needLogin();
    }*/

    @RequestMapping("/getCartItems")
    @ResponseBody
    public responseFromServer getCartItems(@RequestBody Map<String, Object> requestMap, HttpSession session) {
        if (checkSession.check(session)) {
            if(checkSession.checkManager(session)){
                if(requestMap.get("userId")==null)
                    requestMap.put("userId",((User)session.getAttribute("user")).getUserId());
                return cartService.getCartItems(requestMap);
            }else{
                requestMap.put("userId",((User)session.getAttribute("user")).getUserId());
                return cartService.getCartItems(requestMap);
            }
        }
        return responseFromServer.needLogin();
    }

    @RequestMapping("/getCartNum")
    @ResponseBody
    public responseFromServer getCartNum(HttpSession session) {
        if (checkSession.check(session)) {
            User sessionUser = (User) session.getAttribute("user");
            return cartService.getCartNum(sessionUser);
        }
        return responseFromServer.needLogin();
    }


    @RequestMapping("/getCartItem")
    @ResponseBody
    public responseFromServer getCartItem(@RequestBody Map<String, Object> requestMap,HttpSession session) {
        if(checkSession.checkManager(session))
        return cartService.getCartItem(requestMap);
        else if(checkSession.check(session)){
            int userId = (int) requestMap.get("userId");
            if(((User)session.getAttribute("user")).getUserId().intValue()==userId){
                return cartService.getCartItem(requestMap);
            }else{
                return responseFromServer.error("非法操作");
            }
        }
        return responseFromServer.needLogin();
    }

    @RequestMapping("/getCartItemsPage")
    @ResponseBody
    public responseFromServer getCartItemsPage(@RequestBody Map<String, Object> requestMap,HttpSession session) {
        if(checkSession.checkManager(session))
            return cartService.getCartItemsPage(requestMap);
        else if(checkSession.check(session)){
            Integer userId = (Integer) requestMap.get("userId");
            if(userId==null||userId.intValue()==((User)session.getAttribute("user")).getUserId().intValue()){
                return cartService.getCartItemsPage(requestMap);
            }else{
                return responseFromServer.error("非法操作");
            }
        }
        return responseFromServer.needLogin();
    }

    @RequestMapping("/insertCartItem")
    @ResponseBody
    public responseFromServer insertCartItem(@RequestBody CartItem cartItem,HttpSession session) {
        if(checkSession.checkManager(session)){
            if(cartItem.getUserId()==null)
                cartItem.setUserId(((User)session.getAttribute("user")).getUserId().intValue());
            return cartService.insertCartItem(cartItem);
        }else if(checkSession.check(session)){
            cartItem.setUserId(((User)session.getAttribute("user")).getUserId().intValue());
            return cartService.insertCartItem(cartItem);
        }
        return responseFromServer.needLogin();
    }


    @RequestMapping("/updateCartItem")
    @ResponseBody
    public responseFromServer updateCartItem(@RequestBody CartItem cartItem,HttpSession session) {
        if(checkSession.checkManager(session)){
            if(cartItem.getUserId()==null){
                cartItem.setUserId(((User)session.getAttribute("user")).getUserId().intValue());
            }
            return cartService.updateCartItem(cartItem);
        }
        else if(checkSession.check(session)){
            cartItem.setUserId(((User)session.getAttribute("user")).getUserId().intValue());
            return cartService.updateCartItem(cartItem);
        }
        return responseFromServer.needLogin();
    }


    @RequestMapping("/updateCartItems")
    @ResponseBody
    public responseFromServer updateCartItems(@RequestBody List<CartItem> cartItems,HttpSession session) {
        if(checkSession.checkManager(session))
        return cartService.updateCartItems(cartItems);
        else
            return responseFromServer.needLogin();
    }

    @RequestMapping("/deleteCartItem")
    @ResponseBody
    public responseFromServer deleteCartItem(@RequestBody CartItem cartItem,HttpSession session) {
        if(checkSession.checkManager(session)){
            if(cartItem.getUserId()==null){
                cartItem.setUserId(((User)session.getAttribute("user")).getUserId().intValue());
            }
            return cartService.updateCartItem(cartItem);
        }
        else if(checkSession.check(session)){
            cartItem.setUserId(((User)session.getAttribute("user")).getUserId().intValue());
            return cartService.deleteCartItem(cartItem);
        }
        return responseFromServer.needLogin();
    }


    @RequestMapping("/deleteCartItems")
    @ResponseBody
    public responseFromServer deleteCartItems(@RequestBody List<CartItem> cartItems,HttpSession session) {
        if(checkSession.checkManager(session))
            return cartService.deleteCartItems(cartItems);
        else
            return responseFromServer.needLogin();
    }
}
