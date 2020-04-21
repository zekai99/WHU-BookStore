package com.bookshop.entity;
/**
 * @ClassName: CartItem
 * @Description: 购物车项实体类 包含了书籍和对应的数量
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:55
 */
public class CartItem {

    private Integer userId;
    private Integer bookId;
    private String time;
    private Book book;
    private Integer cartNum;

    public CartItem(Integer userId, Integer bookId, Book book, Integer cartNum,String time) {
        this.userId = userId;
        this.bookId = bookId;
        this.book = book;
        this.cartNum = cartNum;
        this.time = time;
    }

    public CartItem() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getCartNum() {
        return cartNum;
    }

    public void setCartNum(Integer cartNum) {
        this.cartNum = cartNum;
    }
}
