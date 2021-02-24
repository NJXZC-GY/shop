package com.graduation.controller;

import com.graduation.entity.Item;
import com.graduation.entity.User;
import com.graduation.service.ItemService;
import com.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;//这里的 userService大小写 需要和   @Service("userService")  一致
    @Autowired
    ItemService itemService;//加载ItemService

    //@GetMapping("/toAllUser")   指定访问请求方式  只能为get 请求
    //访问地址:  http://localhost:8080/project1/user/toAllUser
    @RequestMapping("/toAllUser")
    public ModelAndView toAllUser(ModelAndView mav)throws Exception{
        //调用业务逻辑中查询所有用户的方法
        List<User> users = userService.findAllUsers();
        mav.setViewName("alluser");//设置跳转页面
        mav.addObject("users",users);//将查询到的信息 放入Request域中
        return mav;
    }

    //访问地址:  http://localhost:8080/project1/user/toAllUser2
    @RequestMapping("/toAllUser2")
    public ModelAndView toAllUser2(ModelAndView mav)throws Exception{
        //调用业务逻辑中查询所有用户的方法
        List<User> users = userService.findAllUsers2();
        mav.setViewName("alluser");//设置跳转页面
        mav.addObject("users",users);//将查询到的信息 放入Request域中
        return mav;
    }
    //http://localhost:8080/project1/user/login1   只能post 访问
    @RequestMapping("/login1")
    public ModelAndView login1(ModelAndView mav,User user)throws Exception{
        System.out.println("接收到的用户数据:"+user.toString());
        String result = userService.login(user);
        switch (result){
            case "nameErr":
                mav.setViewName("signin");
                mav.addObject("msg","用户名错误！");
                break;
            case "pwdErr":
                mav.setViewName("signin");
                mav.addObject("msg","密码错误！");
                break;
            case "custom":
                //该段是普通用户登陆之后的效果，登陆后要能看到，登陆后才能看的内容，可能是商品、电影、具体信息
                //能够查询出该用户有权限看到的信息（商品为例，可以看到按种类区分的商品、按照热度排名的商品、可能是各种各样的）
                List<User> users = userService.findAllUsers2();
                mav.setViewName("alluser");//设置跳转页面
                mav.addObject("users",users);//将查询到的信息 放入Request域中
                break;
            case "stateErr":
                mav.setViewName("signin");
                mav.addObject("msg","账户不可用！");
                break;
            case "admin":
                mav.setViewName("admin");//设置跳转管理员页面
                System.out.println("管理员登陆");
                break;
            case "other":
                mav.setViewName("other");//设置跳转其他页面
                System.out.println("其他用户，暂未完善");
                break;
        }
        return mav;
    }
    //优化登陆方法
    @RequestMapping("/login2")
    public ModelAndView login2(ModelAndView mav, User user, HttpSession session)throws Exception{
        System.out.println("接收到的用户数据:"+user.toString());
        String result = userService.login(user);
        List<Item> allItems = new ArrayList<>();
        List<Item> heartItems = new ArrayList<>();
        switch (result){
            case "nameErr":
                mav.setViewName("signin");
                mav.addObject("msg","用户名错误！");
                break;
            case "pwdErr":
                mav.setViewName("signin");
                mav.addObject("msg","密码错误！");
                break;
            case "custom":
                //该段是普通用户登陆之后的效果，登陆后要能看到，登陆后才能看的内容，可能是商品、电影、具体信息
                //能够查询出该用户有权限看到的信息（商品为例，可以看到按种类区分的商品、按照热度排名的商品、可能是各种各样的）
                //之前普通用户查询的是所有商品
                //allItems = itemService.findAllItems();
                //普通用户，只能查询已经上架的商品
                Item item = new Item();
                item.setItemState(1);//我们设计的  商品状态为1  是上架商品
                System.out.println("查询条件:"+item.toString());
                allItems = itemService.findAllItemsByCondition(item);
                heartItems = itemService.findItemsByItemHeart();
                mav.setViewName("itemIndex");//设置跳转页面
                mav.addObject("allItems",allItems);//将查询到的所有商品 放入Request域中
                mav.addObject("heartItems",heartItems);//将查询到的热销商品 放入Request域中
                User loginUser = userService.findUserByUserName(user);
                session.setAttribute("loginUser",loginUser);//登陆的用户信息  放入Session
                break;
            case "stateErr":
                mav.setViewName("signin");
                mav.addObject("msg","账户不可用！");
                break;
            case "admin":
                mav.setViewName("admin");//设置跳转管理员页面
                System.out.println("管理员登陆");
                break;
            case "other":
                //mav.setViewName("other");//设置跳转其他页面
                System.out.println("其他用户，暂未完善");
                //查询所有商品
                //allItems = itemService.findAllItems();
                Item item2 = new Item();
                System.out.println("查询条件:"+item2.toString());
                allItems = itemService.findAllItemsByCondition(item2);
                heartItems = itemService.findItemsByItemHeart();
                mav.setViewName("itemIndex");//设置跳转页面
                mav.addObject("allItems",allItems);//将查询到的所有商品 放入Request域中
                mav.addObject("heartItems",heartItems);//将查询到的热销商品 放入Request域中
                loginUser = userService.findUserByUserName(user);
                session.setAttribute("loginUser",loginUser);//登陆的用户信息  放入Session
                break;
        }
        return mav;
    }
}
