package com.bookshop.entity;

import java.util.List;

/**
 * @ClassName: Order
 * @Description: 订单实体类
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:57
 */
public class Order {
    private Integer orderId;
    private Integer userId;
    private String orderStatus;//订单状态
    private String orderAddress;//订单地址
    private String purchaseTime;
    private Double price;
    private List<OrderItem> books;
    private User user;

    public Order(Integer orderId, Integer userId, String orderStatus, String orderAddress, String purchaseTime, Double price, List<OrderItem> books, User user) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.orderAddress = orderAddress;
        this.purchaseTime = purchaseTime;
        this.price = price;
        this.books = books;
        this.user = user;
    }

    public Order() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public List<OrderItem> getBooks() {
        return books;
    }

    public void setBooks(List<OrderItem> books) {
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

}
