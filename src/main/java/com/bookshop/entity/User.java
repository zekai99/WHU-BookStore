package com.bookshop.entity;

import java.util.HashMap;

import static com.bookshop.util.Util.checkStringIsEmpty;

/**
 * @ClassName: OrderItem
 * @Description: 用户实体类
 * @Author: 曾志昊
 * @Date: 2020/3/28 2:00
 */
public class User {
    private Integer userId;
    private String userName;
    private String gender;
    private String userAddress;
    private String userPassword;
    private String userStatus;

    public HashMap<String,Object> toMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("userId",userId==null?null:userId+"");
        map.put("userName",checkStringIsEmpty(userName));
        map.put("gender",checkStringIsEmpty(gender));
        map.put("userAddress",checkStringIsEmpty(userAddress));
        map.put("userPassword",checkStringIsEmpty(userPassword));
        map.put("userStatus",checkStringIsEmpty(userStatus));
        return map;
    }


    public User(Integer userId, String userName, String gender, String userAddress, String userPassword, String userStatus) {
        this.userId = userId;
        this.userName = userName;
        this.gender = gender;
        this.userAddress = userAddress;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
