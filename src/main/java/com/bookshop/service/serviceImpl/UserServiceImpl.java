package com.bookshop.service.serviceImpl;

import com.bookshop.common.responseFromServer;
import com.bookshop.dao.UserDao;
import com.bookshop.entity.User;
import com.bookshop.service.UserService;
import com.bookshop.entity.Page;
import com.bookshop.util.configs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @Author: 曾志昊
 * @Date: 2020/3/28 2:02
 */
@Service(value = "UserService")
public class UserServiceImpl implements UserService {

    UserDao userDao;

    /*find list of users by query of username
    * 通过用户名来查找用户*/
    public responseFromServer getUserByUserName(String userName){
        Map<String,Object> map = new HashMap<>();
        map.put("userName",userName);
        List<User> users = userDao.searchUsers(map);
//        return users;
        return responseFromServer.success(users);
    }

    /*check if user with a certain name exists
    * 查找是否存在特定用户名的用户*/
    public responseFromServer checkUserExistedByUserName(String userName){
        Map<String,Object> map = new HashMap<>();
        map.put("userName",userName);
        List<User> users = userDao.searchUsers(map);
        if(users!=null && users.size()>0)return responseFromServer.success();
        return responseFromServer.error("不存在该名称的用户");
    }

    public boolean checkUserExistedByUserNameBoolean(String userName){
        Map<String,Object> map = new HashMap<>();
        map.put("userName",userName);
        List<User> users = userDao.searchUsers(map);
        if(users!=null && users.size()>0)return true;
        return false;
    }

    public responseFromServer checkUserExistedByUserId(Integer userId){
        User user = userDao.getUser(userId);
        if(user!=null)return responseFromServer.success(user);
        return responseFromServer.error("不存在该id用户");
    }

    /*insert user
    * 插入用户*/
    @Transactional(rollbackOn = Exception.class)
    public responseFromServer insertUser (User user) {
        Map<String,Object> map = new HashMap<>();
        map.put("userName",user.getUserName());
        List<User> users = userDao.searchUsers(map);
        if(users!=null && users.size()>0){
//            /*存在同名用户 注册失败*/
//            throw new UserNameIsUsedException(user);
            return responseFromServer.error("用户名已被注册");
        }else{
            try{
                /*the generated key has been assigned to the property "userId" of object "user" now
                 * 此时数据库生成的主键id返回并且更新到user对象中*/
                userDao.insertUser(user);
            }catch (Exception e){
                e.printStackTrace();
//                throw new Exception();
            }
        }
        if(user.getUserId()!=null){
            return responseFromServer.success("注册成功",user);
        }else{
            return responseFromServer.error("注册失败");
        }
//        return user;
    }

    public responseFromServer insertUsers (List<User> users){
        int rows = 0;
        for(int i =0;i<users.size();i++){
            userDao.insertUser(users.get(i));
            if(users.get(i).getUserId()!=null){
                rows ++;
            }
        }
        if(rows<users.size()){
            return responseFromServer.error("成功插入("+rows+"/"+users.size()+")个用户");
        }else{
            return responseFromServer.success("所有用户全部成功插入");
        }
    }


    /*delete an user (close an account)
    * 删除用户（注销账号）*/
    public responseFromServer deleteUser(User user){
        if(user!=null){
            Map<String,Object> map = new HashMap<>();
            if(user.getUserId()==null)return responseFromServer.error("没有用户id");
            map.put("userId",user.getUserId());
            List<User> users = userDao.searchUsers(map);
            if(users!=null && users.size()==1){
                userDao.deleteUserById(user.getUserId());
                return responseFromServer.success("删除账号成功");
            }
        }
        return responseFromServer.error("删除账号失败");
//            return false;

    }

    /*user login
    * 用户登录*/
    public responseFromServer login(User user){
        if(user!=null){
            Map<String,String> map = new HashMap<>();
            /*查询用户名*/
            map.put("userName",user.getUserName());
            map.put("userPassword",user.getUserPassword());
            List<User> loginUser = userDao.searchUsers(map);
            if(loginUser!=null||loginUser.size()==1){
                User verifiedUser = loginUser.get(0);
                verifiedUser.setUserPassword("");
                return responseFromServer.success("登录成功",verifiedUser);
//                return verifiedUser;
            }else{
                return responseFromServer.error("密码或用户名错误");
//                throw new RuntimeException("密码或用户名错误");
            }
        }else{
            return responseFromServer.error("登录错误");
//            throw new RuntimeException("登录错误（user为空）");
        }

    }


    private Page getPage(Map<String,Object> queryMap){
        Page<User> page = new Page<User>(configs.pageSize);
        Integer startPage = (Integer)(queryMap.get("startPage"));
        queryMap.put("startPage",(startPage-1)*configs.pageSize);
        queryMap.put("pageSize",configs.pageSize);
        page.setCurrPage(startPage);
        page.setTotalCount(userDao.count(queryMap));
        page.setTotalPage(((Double)Math.ceil((double)page.getTotalCount()/(double)configs.pageSize)).intValue());
        page.setLists(userDao.searchUsers(queryMap));
        return page;
    }



    public responseFromServer searchUsersPage(Map<String,Object> queryMap){
        if(queryMap.get("startPage")==null){
            queryMap.put("startPage",1);
        }
        return responseFromServer.success(getPage(queryMap));
    }

    @Override
    public responseFromServer getUser(Integer userId) {
        User user = userDao.getUser(userId);
        if(user == null){
            return responseFromServer.error();
        }else{
            return responseFromServer.success(user);
        }
    }

    @Override
    public responseFromServer getAllUsersPage(Integer startPage) {
        HashMap<String,Object> map = new HashMap<>();
        Page<User> page = new Page<User>(configs.pageSize);
        page.setTotalCount(userDao.count(map));
        page.setCurrPage(startPage);
        page.setTotalPage(((Double)Math.ceil(page.getTotalCount()/configs.pageSize)).intValue());
        map.put("pageSize",configs.pageSize);
        map.put("startPage",startPage);
        page.setLists(userDao.getAllUsersPage(map));
        return responseFromServer.success(page);
    }

    @Override
    public responseFromServer getAllUsers() {
        return responseFromServer.success(userDao.getAllUsers());
    }


    /*更新后重新查询查看是否更新成功*/
    @Override
    @Transactional
    public responseFromServer updateUser(User user) {
        int rows = userDao.updateUser(user);
        if(rows!=1){
            return responseFromServer.error("更新用户失败");
        }
        return responseFromServer.success("更新用户成功",user);
    }

    @Override
    public responseFromServer updateUsers(List<User> users) {
        if(users == null||users.size()==0)return responseFromServer.error("待删除用户表为空");
        return responseFromServer.success(userDao.updateUsers(users));
    }



    @Override
    public responseFromServer deleteUsers(List<User> users) {
        /*当影响行数小于用户个数时返回错误*/
        int rows = userDao.deleteUsers(users);
        if(rows<users.size()){
            return responseFromServer.error(rows+"(/"+users.size()+")个用户删除成功");
        }else return responseFromServer.success("所有用户删除成功");
    }

    /*通过id删除user*/
    @Override
    public responseFromServer deleteUserBytId(Integer userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        List<User> users = userDao.searchUsers(map);
        if(users!=null && users.size()==1){
            return responseFromServer.success("删除用户成功",userDao.deleteUserById(userId));
        }else{
            return responseFromServer.error("删除用户失败");
        }
    }

    /*依据map查询个数*/
    public responseFromServer count(Map map){
        return responseFromServer.success(userDao.count(map));
//        return userDao.count(map);
    }

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }
}
