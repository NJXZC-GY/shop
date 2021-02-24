package com.graduation.service;

import com.graduation.entity.User;

import java.util.List;

public interface UserService {
    //查询所有用户业务逻辑 查询后，返回所有用户信息
    List<User> findAllUsers();
    //测试xml实现 mybatis 查询
    List<User> findAllUsers2();
    //登录功能业务逻辑  考虑到登录的情况比较多
    String login(User user);
    //根据用户名查询用户业务逻辑
    User findUserByUserName(User user);
    //用户信息修改业务逻辑

    //查询用户及其订单、地址
    User findUserAndOrders(int userId);
}
