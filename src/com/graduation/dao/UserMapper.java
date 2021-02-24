package com.graduation.dao;

import com.graduation.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("SELECT USER_ID userId,USER_NAME userName,pwd,sex,birth,state,role," +
            "USER_IMAGE userImage,phone,score,balance FROM user")
    List<User> findAllUsers();
    //使用xml编写SQL 语句
    List<User> findAllUsers2();
    //根据用户名查询用户  如果传递多个值  请使用对象
    @Select("SELECT USER_ID userId,USER_NAME userName,pwd,sex,birth,state,role,USER_IMAGE userImage,phone,score,balance " +
            "FROM user WHERE USER_NAME=#{userName}")
    User findUserByUserName(User user);
    /*考虑到实际情况  以及页面  我们以   用户作为主体，先作为返回值*/
    User findUserAndOrders(int userId);
}
