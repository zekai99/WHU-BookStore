package com.bookshop.controller;

import com.bookshop.common.checkSession;
import com.bookshop.common.responseFromServer;
import com.bookshop.entity.User;
import com.bookshop.service.UserService;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserController
 * @Description:
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:46
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("/checkLogin")
    @ResponseBody
    public responseFromServer checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null && user.getUserId() != null) {
            user.setUserPassword("");
            return responseFromServer.success(user);
        }
        return responseFromServer.needLogin();
    }


    @RequestMapping("/register")
    @ResponseBody
    public responseFromServer register(@RequestBody User user, HttpSession session) {
        responseFromServer response = userService.insertUser(user);
        if (response.isSuccess()) {
            session.setAttribute("user", (User) response.getData());
        }
        return response;
    }

    @RequestMapping("/registerUsers")
    @ResponseBody
    public responseFromServer registerUsers(@RequestBody List<User> users, HttpSession session) {
        if (checkSession.checkManager(session)) {
            Map<String, Object> result = new HashMap<>();
            List<User> errorUsers = new ArrayList<>();
            for (User user : users) {
                try {
                    userService.insertUser(user);
                } catch (Exception e) {
                    errorUsers.add(user);
                }
            }
            if (errorUsers.size() > 0) {
                result.put("error", "批量注册用户失败");
                result.put("errorUsers", errorUsers);
            }
            return userService.insertUsers(users);
        } else {
            return responseFromServer.needLogin();
        }
    }


    /*user要带id*/
    /*todo 删除账号密码校验*/
    @RequestMapping("/closeAccount")
    @ResponseBody
    public responseFromServer closeAccount(@RequestBody User user, HttpSession session) {
        if (checkSession.checkManager(session)) {
            return userService.deleteUser(user);
        } else {
            return responseFromServer.needLogin();
        }
    }

    @RequestMapping("/setsession")
    @ResponseBody
    public responseFromServer setsession(HttpSession session) {
        session.setAttribute("mysession", "fuckyou");
        return responseFromServer.success();
    }

    @RequestMapping("/testsession")
    @ResponseBody
    public responseFromServer testsession(HttpSession session) {
        String user = (String) session.getAttribute("mysession");
        return responseFromServer.success();
    }


    @RequestMapping("/sessiontest")
    @ResponseBody
    public responseFromServer sessionTest(HttpSession session) {
        User nowUser = (User) session.getAttribute("user");
        return null;
    }


    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public responseFromServer login(@RequestBody User user, HttpSession session) {
        responseFromServer response = userService.login(user);
        if (response.isSuccess()) {
            User nowUser = (User) response.getData();
            nowUser.setUserPassword(null);
            session.setAttribute("user", nowUser);
            return responseFromServer.success(nowUser);
        }
        return response;
    }


    @RequestMapping("/logOut")
    @ResponseBody
    public responseFromServer logOut(HttpSession session) {
        if ((User) session.getAttribute("user") == null) {
            return responseFromServer.error("暂无登录信息");
        } else {
            session.removeAttribute("user");
            return responseFromServer.success();
        }
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public responseFromServer getUser(@RequestBody User user, HttpSession session) {
        if (checkSession.checkManager(session))
            return userService.getUser(user.getUserId());
        else if (checkSession.check(session)) {
            /*user只能获取自己的用户信息*/
            if (((User) session.getAttribute("user")).getUserId().intValue() != user.getUserId().intValue()) {
                return userService.getUser(user.getUserId());
            } else
                return responseFromServer.error("非法操作");
        }
        return responseFromServer.needLogin();
    }


    @RequestMapping("/searchUsersPage")
    @ResponseBody
    public responseFromServer searchUsersPage(@RequestBody Map<String, Object> requestMap, HttpSession session) {
        if (checkSession.checkManager(session)) {
            return userService.searchUsersPage(requestMap);
        } else return responseFromServer.needLogin();
    }

    @RequestMapping("/getAllUsers")
    @ResponseBody
    public responseFromServer getAllUsers(HttpSession session) {
        if (checkSession.checkManager(session))
            return userService.getAllUsers();
        return responseFromServer.needLogin();
    }

    @RequestMapping("/getAllUsersPage")
    @ResponseBody
    public responseFromServer getAllUsersPage(@RequestBody Integer startPage, HttpSession session) {
        if (checkSession.checkManager(session))
            return userService.getAllUsersPage(startPage);
        return responseFromServer.needLogin();
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public responseFromServer updateUser(@RequestBody User user, HttpSession session) {
        if(checkSession.checkManager(session)){
            if(user.getUserId()==null){
                user.setUserId(((User)session.getAttribute("user")).getUserId());
            }
            responseFromServer response =  userService.updateUser(user);
            if(response.isSuccess()){
                session.removeAttribute("user");
                session.setAttribute("user",(User)userService.getUser(user.getUserId()).getData());
            }
            return response;
        }else if (checkSession.check(session)){
            User loginUser =(User)session.getAttribute("user");
            if(user.getUserId()!=null&&loginUser.getUserId()!=user.getUserId()){
                return responseFromServer.error("非法操作");
            }
            user.setUserId(loginUser.getUserId());
            responseFromServer response =  userService.updateUser(user);
            if(response.isSuccess()){
                session.removeAttribute("user");
                session.setAttribute("user",(User)userService.getUser(user.getUserId()).getData());
            }
            return response;
        }
        return responseFromServer.needLogin();
    }

    @RequestMapping("/updateUsers")
    @ResponseBody
    public responseFromServer updateUsers(@RequestBody List<User> users, HttpSession session) {
        if (checkSession.checkManager(session))
            return userService.updateUsers(users);
        return responseFromServer.needLogin();
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public responseFromServer deleteUser(@RequestBody User user, HttpSession session) {
        if (checkSession.checkManager(session))
            return userService.deleteUser(user);
        else if (checkSession.check(session)) {
            if (((User) session.getAttribute("user")).getUserId().intValue() == user.getUserId().intValue()) {
                return userService.deleteUser(user);
            } else {
                return responseFromServer.error("非法操作");
            }
        }
        return responseFromServer.error();
    }

    @RequestMapping("/deleteUsers")
    @ResponseBody
    public responseFromServer deleteUsers(List<User> users, HttpSession session) {
        if (checkSession.checkManager(session))
            return userService.deleteUsers(users);
        else return responseFromServer.needLogin();
    }
}
