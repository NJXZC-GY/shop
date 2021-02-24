package com.graduation.service;

import com.graduation.dao.UserMapper;
import com.graduation.entity.Item;
import com.graduation.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

// ctrl+i
@Transactional
@Service("userService")  //定义UserServiceImpl是一个Service  命名为   userService
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;  //通过注解 载入 UserMapper

    @Override
    public List<User> findAllUsers() {
        System.out.println("执行了查询所有用户的业务逻辑"); //sout  打印快捷键
        return userMapper.findAllUsers();//调用UserMapper中  查询所有用户的方法
    }

    @Override
    public List<User> findAllUsers2() {
        System.out.println("执行了查询所有用户的业务逻辑（xml版）");
        return userMapper.findAllUsers2();
    }

    @Override
    public String login(User user) {
        //1.根据用户名查询用户
        User getUser = userMapper.findUserByUserName(user);
        if(null==getUser){ //用户不存在
            return "nameErr";
        }else{
            if (getUser.getPwd().equals(user.getPwd())){
                //还需要区分是否状态可用、角色等  暂时省略
                if(1==getUser.getState()){//状态可用
                    if(1==getUser.getRole()){
                        return "custom";
                    }else if(9==getUser.getRole()){
                        return "admin";
                    }else {
                        return "other";//以后补充
                    }
                }else{//状态不可用
                    return "stateErr";
                }
//                return "success";//登录成功
            }else{
                return "pwdErr";//密码错误
            }
        }
    }

    @Override
    public User findUserByUserName(User user) {
        System.out.println("根据用户名查询用户");
        return userMapper.findUserByUserName(user);
    }

    @Override
    public User findUserAndOrders(int userId) {
        return userMapper.findUserAndOrders(userId);
    }
}
