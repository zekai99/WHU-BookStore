package com.bookshop.service;

import com.bookshop.common.responseFromServer;
import com.bookshop.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: UserService
 * @Description:
 * @Author: 曾志昊
 * @Date: 2020/3/28 2:00
 */
public interface UserService {
    responseFromServer checkUserExistedByUserName(String userName);
    responseFromServer login(User user);

    responseFromServer getUser(Integer userId);
    responseFromServer getAllUsersPage(Integer startPage);
    responseFromServer getAllUsers();

    responseFromServer searchUsersPage(Map<String,Object> queryMap);

    responseFromServer insertUser(User user);
    responseFromServer insertUsers(List<User> users);

    responseFromServer updateUser(User user);
    responseFromServer updateUsers(List<User> users);

    responseFromServer deleteUserBytId(Integer userId);
    responseFromServer deleteUser(User user);
    responseFromServer deleteUsers(List<User> users);

    responseFromServer count(Map map);


    /*boolean checkUserExistedByUserName(String userName);
    User login(User user);

    User getUser(Integer userId);
    ResponseFromServer<Page<User>> getAllUsersPage(Integer startPage);
    List<User> getAllUsers();

    Page<User> searchUsersPage(Map<String,Object> queryMap);

    User insertUser(User user) throws Exception;

    Boolean updateUser(User user);
    Integer updateUsers(List<User> users);

    Boolean deleteUserBytId(Integer userId);
    Boolean deleteUser(User user);
    Integer deleteUsers(List<User> users);

    Integer count(Map map);
*/
}
