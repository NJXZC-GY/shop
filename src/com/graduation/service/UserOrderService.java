package com.graduation.service;

import com.graduation.entity.Item;
import com.graduation.entity.UserOrder;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

public interface UserOrderService {
    //根据用户编号查询订单及地址
    List<UserOrder> findOrderAndAddress(int userId);
    //根据订单编号查询订单及地址及详情
    UserOrder findOrderAndAddressAndDesc(int orderId);
    //直接购买功能  先判断是否能够购买  比如，用户是否登录、钱是否够
    String buyItem1(HttpSession session, Item item,int buyCount);
    //插入订单业务逻辑
    boolean insertUserOrder(UserOrder userOrder);
}
