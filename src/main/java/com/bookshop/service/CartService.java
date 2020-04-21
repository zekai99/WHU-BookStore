package com.bookshop.service;

import com.bookshop.common.responseFromServer;
import com.bookshop.entity.CartItem;
import com.bookshop.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: CartService
 * @Description:
 * @Author: 曾志昊
 * @Date: 2020/3/28 2:00
 */
public interface CartService {

    /*根据用户id获取整个购物车*/
    responseFromServer getCart(Map<String,Object> requestMap);
    /*根据用户id获取所有购物车商品*/
    responseFromServer getCartItems(Map<String,Object> requestMap);
    /*根据用户id获取购物车分页*/
    responseFromServer getCartItemsPage(Map<String,Object> requestMap);
    /*根据用户id和bookid获取完整item数据*/
    responseFromServer getCartItem(Map<String,Object> requestMap);

    responseFromServer getCartNum(User user);

    responseFromServer insertCartItem(CartItem cartItem);
    responseFromServer updateCartItem(CartItem cartItem);
    responseFromServer updateCartItems(List<CartItem> cartItems);
    responseFromServer deleteCartItem(CartItem cartItem);
    responseFromServer deleteCartItems(List<CartItem> cartItems);


}
