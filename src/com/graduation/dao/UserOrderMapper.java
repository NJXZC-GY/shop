package com.graduation.dao;

import com.graduation.entity.User;
import com.graduation.entity.UserOrder;

import java.util.List;

public interface UserOrderMapper {

    /*1.根据用户编号、查询所有订单信息
    *   一个用户可以有多个订单  用户 对 订单  一对多
    *
    *   订单对 用户  一个订单只能属于一个用户   一对一
    *   订单 对 订单详情   一个订单可以有多条详情记录    订单 对 订单详情  一对多
    *   订单 对 收货地址   一个订单只可能有一个地址  一对一
    *
    *   以订单作为主体（作为返回值类型）
    * */

    //只查订单及其地址
    List<UserOrder> findOrderAndAddress(int userId);

    //根据订单号查询订单及其详情、地址等 一个订单号只可能查出一个订单，但是订单可以有多个详情条目
    UserOrder findOrderAndAddressAndDesc(int orderId);
    //新增订单信息
    int insertUserOrder(UserOrder userOrder);
}
