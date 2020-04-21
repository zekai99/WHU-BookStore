package com.bookshop.dao;

import com.bookshop.entity.Cart;

/**
 * @InterfaceName: CartDao
 * @Description: 没用的接口
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:50
 */
public interface CartDao {
    Cart getCart(Integer userId);

 /* 这些都在CartItemDao中实现了
    addBook(CartItem cartItem,Integer userId);//购物车增加一本书
    void addBooks(List<CartItem> cartItems,Integer userId);//购物车增加多个书籍

    void deleteCartItem(CartItem cartItem,Integer userId);
    void deleteCartItems(List<CartItem> cartItems,Integer userId);

    void updateCartItem(CartItem cartItem);//修改，用实体作为参数
    void updateCartItems(List<CartItem> cartItems);*/
}