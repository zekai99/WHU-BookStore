package com.bookshop.dao;

import com.bookshop.entity.Cart;
import com.bookshop.entity.CartItem;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: CartItemDao
 * @Description: 购物车条目
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:52
 */
public interface CartItemDao {
    List<CartItem> getCartItems(Map map);
    CartItem getCartItem(Map map);

    /*根据userid bookid查询个数*/
    CartItem getCartItemSimple(Map map);

    Integer insertCartItem(CartItem cartItem);

    Integer updateCartItem(CartItem cartItem);
    Integer updateCartItems(List<CartItem> cartItems);

    Integer deleteCartItem(CartItem cartItem);
    Integer deleteCartItems(List<CartItem> cartItems);

    Integer count(Map map);

}