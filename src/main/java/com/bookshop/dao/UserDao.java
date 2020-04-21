package com.bookshop.dao;

import com.bookshop.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: UserDao
 * @Description: user操作
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:53
 */
public interface UserDao {
    User getUser(Integer userId);
    List<User> getAllUsersPage(Map map);
    List<User> getAllUsers();

    List<User> searchUsers(Map map);

    Integer count(Map map);

    void insertUser(User user);
    void insertUsers(List<User> users);//插入，用实体作为参数

    Integer updateUser(User user);
    Integer updateUsers(List<User> users);

    Integer deleteUser(User user);
    Integer deleteUserById(Integer userId);
    Integer deleteUsers(List<User> userIds);

}
