package com.bookshop.dao;

import com.bookshop.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: OrderDao
 * @Description: OrderDao
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:52
 */
public interface OrderDao {
    Order getOrder(Integer orderId);
    List<Order> getOrdersByUserId(Integer userId);
    List<Order> getOrdersPlusByUserId(Integer userId);

    List<Order> searchOrders(Map map);

    Integer insertOrder(Order order);
    Integer insertOrders(List<Order> orders);

    Integer updateOrder(Order order);
    Integer updateOrders(List<Order> orders);

    Integer deleteOrderById(Integer orderId);
    Integer deleteOrder(Order order);
    void deleteOrders(List<Integer> orderIds);

    Integer count(Map map);
}
