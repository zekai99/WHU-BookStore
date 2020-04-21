package com.bookshop.service;

import com.bookshop.common.responseFromServer;
import com.bookshop.entity.Order;
import com.bookshop.entity.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: OrderService
 * @Description: OrderService
 * @Author: 曾志昊
 * @Date: 2020/3/28 2:01
 */
public interface OrderService {
    responseFromServer getOrder(Integer orderId);
    responseFromServer getOrdersByUserId(Integer userId);
    responseFromServer insertOrder(Order order);
    responseFromServer purchase(Order order);

    responseFromServer searchOrdersPage(Map<String,Object> queryMap);
    responseFromServer getOrdersPlusByUserId(Map<String,Object>queryMap);

    responseFromServer insertOrders(List<Order> orders);
    responseFromServer updateOrder(Order order);
    responseFromServer updateOrders(List<Order> orders);
    responseFromServer deleteOrderById(Integer orderId);
    responseFromServer deleteOrder(Order order);
    responseFromServer deleteOrders(List<Order> orders);
    responseFromServer insertOrderItem(OrderItem orderItem);
    responseFromServer updateOrderItem(OrderItem orderItem);
    responseFromServer updateOrderItem(List<OrderItem> orderItems);
    responseFromServer deleteOrderItem(OrderItem orderItem);
    responseFromServer deleteOrderItemsByOrderId(Integer orderId);
}
