package com.bookshop.entity;

import java.util.List;

/**
 * @ClassName: Cart
 * @Description: 购物车实体类 包含了所有的购物车项
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:55
 */
public class Cart {
    //购物车书本
    private List<CartItem> books;
    private Integer userId;


    public Cart(List<CartItem> books, Integer userId) {
        this.books = books;
        this.userId = userId;
    }

    public Cart() {
    }

    public List<CartItem> getBooks() {
        return books;
    }

    public void setBooks(List<CartItem> books) {
        this.books = books;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
