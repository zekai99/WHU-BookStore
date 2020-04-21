package com.bookshop.entity;

/**
 * @ClassName: OrderItem
 * @Description: 订单项实体类
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:59
 */
public class OrderItem {
    private Integer orderId;
    private Integer bookId;
    private Integer orderNum;
    private String orderItemStatus;
    private Book book;

    public OrderItem(Integer orderId, Integer bookId, Integer orderNum, String orderItemStatus, Book book) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.orderNum = orderNum;
        this.orderItemStatus = orderItemStatus;
        this.book = book;
    }

    public String getOrderItemStatus() {
        return orderItemStatus;
    }

    public void setOrderItemStatus(String orderItemStatus) {
        this.orderItemStatus = orderItemStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public OrderItem() {
    }
}
