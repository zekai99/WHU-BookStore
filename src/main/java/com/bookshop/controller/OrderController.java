package com.bookshop.controller;

import com.bookshop.common.checkSession;
import com.bookshop.common.responseFromServer;
import com.bookshop.dao.OrderDao;
import com.bookshop.dao.UserDao;
import com.bookshop.entity.Book;
import com.bookshop.entity.Order;
import com.bookshop.entity.OrderItem;
import com.bookshop.entity.User;
import com.bookshop.service.OrderService;
import com.bookshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: OrderController
 * @Description:
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:47
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;
    @Resource
    UserService userService;

    @RequestMapping("/purchase")
    @ResponseBody
    public responseFromServer purchase(@RequestBody Map<String,Object> map,HttpSession session){
        if(checkSession.check(session)){
            String userPassword = (String) map.get("userPassword");
            Integer orderId = (Integer) map.get("orderId");
//            Integer orderId = (Integer)(map.get("order")==null?null:((Order)map.get("order")).getOrderId());
            if(orderId==null||userPassword==null){
                return responseFromServer.error("错误");
            }
            User user = (User) session.getAttribute("user");
            user.setUserPassword(userPassword);
            responseFromServer response = userService.login(user);
            if(response.isSuccess()){
                Order order = new Order();
                order.setOrderStatus("paid");
                order.setOrderId(orderId);
                return orderService.purchase(order);
            }
            return responseFromServer.error();
        }else{
            return responseFromServer.needLogin();
        }
    }


    @RequestMapping("/purchaseOnDelivery")
    @ResponseBody
    public responseFromServer purchaseOnDelivery(@RequestBody Map<Integer,Object> map,HttpSession session){
        if(checkSession.check(session)){
            Integer orderId = (Integer) map.get("orderId");
            if(orderId==null){
                return responseFromServer.error("错误");
            }
            Order order = new Order();
            order.setOrderStatus("unpaid-ondelivery");
            return orderService.updateOrder(order);
        }else{
            return responseFromServer.needLogin();
        }
    }



    @RequestMapping("/getOrderById")
    @ResponseBody
    public responseFromServer getOrderById(@RequestBody Integer orderId, HttpSession session) {
        if (checkSession.check(session))
            return orderService.getOrder(orderId);
        return responseFromServer.needLogin();
    }

    @RequestMapping("/getOrder")
    @ResponseBody
    public responseFromServer getOrder(@RequestBody Order order, HttpSession session) {
        if (checkSession.check(session))
            return orderService.getOrder(order.getOrderId());
        return responseFromServer.needLogin();
    }

    @RequestMapping("/getOrdersByUserId")
    @ResponseBody
    public responseFromServer getOrdersByUserId(@RequestBody User user, HttpSession session) {
        if (checkSession.checkManager(session))
            return orderService.getOrdersByUserId(user.getUserId());
        else if (checkSession.check(session)) {
            if (((User) session.getAttribute("user")).getUserId().intValue() == user.getUserId().intValue()) {
                return orderService.getOrdersByUserId(user.getUserId());
            } else return responseFromServer.error("非法操作");
        }
        return responseFromServer.needLogin();
    }

    @RequestMapping("/getOrdersForUser")
    @ResponseBody
    public responseFromServer getOrdersForUser(HttpSession session) {
        if (checkSession.check(session)){
            User user = (User)session.getAttribute("user");
            return orderService.getOrdersByUserId(user.getUserId());
        }
        return responseFromServer.needLogin();
    }



    @RequestMapping("/searchOrdersPage")
    @ResponseBody
    public responseFromServer searchOrdersPage(@RequestBody Map<String, Object> requestMap, HttpSession session) {
        if (checkSession.checkManager(session))
            return orderService.searchOrdersPage(requestMap);
        else if (checkSession.check(session)) {
            Integer userId = (Integer) requestMap.get("userId");
            User user = (User) session.getAttribute("user");
            if (userId != null && userId.intValue() == user.getUserId().intValue()) {
                return orderService.searchOrdersPage(requestMap);
            } else {
                return responseFromServer.error("非法操作");
            }
        }
        return responseFromServer.needLogin();
    }



/*
    @RequestMapping("/createOrder")
    @ResponseBody
    public responseFromServer createOrder(@RequestBody )*/

    @RequestMapping("/insertOrder")
    @ResponseBody
    public responseFromServer insertOrder(@RequestBody Order order, HttpSession session) {
        if (checkSession.checkManager(session)) {
            if (order != null && order.getUserId() == null) {
                order.setUserId(((User) session.getAttribute("user")).getUserId());
            }
            return orderService.insertOrder(order);
        } else if (checkSession.check(session)) {
            if (order != null) {
                order.setUserId(((User) session.getAttribute("user")).getUserId());
                return orderService.insertOrder(order);
            } else {
                return responseFromServer.error("非法操作");
            }
        }
        return responseFromServer.needLogin();

            /*        if (order.getUser() == null && order.getUserId() == null) {
            User tempUser = (User) session.getAttribute("user");
            order.setUserId(tempUser.getUserId());
        }*/

    }


    @RequestMapping("/insertOrders")
    @ResponseBody
    public responseFromServer insertOrders(@RequestBody List<Order> orders, HttpSession session) {
        if (checkSession.checkManager(session))
            return orderService.insertOrders(orders);
        return responseFromServer.error("非法操作");
    }


    @RequestMapping("/getOrdersPlusByUserId")
    @ResponseBody
    public responseFromServer getOrdersPlusByUserId(@RequestBody Map<String, Object> reqeustMap) {
        //requestMap中应包含userId
        return orderService.getOrdersPlusByUserId(reqeustMap);
    }

    @RequestMapping("/updateOrder")
    @ResponseBody
    public responseFromServer updateOrder(@RequestBody Order order, HttpSession session) {
        if (checkSession.checkManager(session)){
            if (order != null && order.getUserId() == null) {
                order.setUserId(((User) session.getAttribute("user")).getUserId());
            }
            return orderService.updateOrder(order);
        }
        return responseFromServer.error("非法操作");
    }

    @RequestMapping("/updateOrders")
    @ResponseBody
    public responseFromServer updateOrders(@RequestBody List<Order> orders, HttpSession session) {
        if (checkSession.checkManager(session))
            return orderService.updateOrders(orders);
        return responseFromServer.error("非法操作");
    }

    @RequestMapping("/deleteOrderById")
    @ResponseBody
    public responseFromServer deleteOrderById(@RequestBody Integer orderId, HttpSession session) {
        if (checkSession.checkManager(session))
            return orderService.deleteOrderById(orderId);
        else if (checkSession.check(session)) {
            User user = (User) session.getAttribute("user");
            Order order = (Order) orderService.getOrder(orderId).getData();
            if (order.getUserId() == null) return responseFromServer.error("查询出错");
            if (order.getUserId().intValue() == user.getUserId().intValue()) {
                return orderService.deleteOrderById(orderId);
            } else {
                return responseFromServer.error("非法操作");
            }
        }
        return responseFromServer.needLogin();
    }

    @RequestMapping("/deleteOrder")
    @ResponseBody
    public responseFromServer deleteOrder(@RequestBody Order order, HttpSession session) {
        if (checkSession.checkManager(session)){
            if (order != null && order.getUserId() == null) {
                order.setUserId(((User) session.getAttribute("user")).getUserId());
            }
            return orderService.deleteOrder(order);

        }
        else if (checkSession.check(session)) {
            User user = (User) session.getAttribute("user");
            if (order.getUserId() == null) return responseFromServer.error("查询出错");
            if (order.getUserId().intValue() == user.getUserId().intValue()) {
                return orderService.deleteOrder(order);
            } else {
                return responseFromServer.error("非法操作");
            }
        }
        return responseFromServer.needLogin();
    }

    @RequestMapping("/deleteOrders")
    @ResponseBody
    public responseFromServer deleteOrders(@RequestBody List<Order> orders, HttpSession session) {
        if (checkSession.checkManager(session)) {
            return orderService.deleteOrders(orders);
        }
        return responseFromServer.error("非法操作");
    }

    @RequestMapping("/showItems")
    @ResponseBody
    public responseFromServer showItems(@RequestBody OrderItem item) {
        Book book = item.getBook();
        return responseFromServer.success(book);
    }

    @RequestMapping("/insertOrderItem")
    @ResponseBody
    public responseFromServer insertOrderItem(@RequestBody OrderItem orderItem, HttpSession session) {
        if (checkSession.checkManager(session)) {
            return orderService.insertOrderItem(orderItem);
        }
        return responseFromServer.error("非法操作");
    }


    @RequestMapping("/updateOrderItem")
    @ResponseBody
    public responseFromServer updateOrderItem(List<OrderItem> orderItems, HttpSession session) {
        if (checkSession.checkManager(session)) {
            return orderService.updateOrderItem(orderItems);
        }
        return responseFromServer.error("非法操作");
    }


    @RequestMapping("/deleteOrderItem")
    @ResponseBody
    public responseFromServer deleteOrderItem(OrderItem orderItem, HttpSession session) {
        if (checkSession.checkManager(session)) {
            return orderService.deleteOrderItem(orderItem);
        }
        return responseFromServer.error("非法操作");
    }

}
