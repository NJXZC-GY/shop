package com.graduation.controller;

import com.graduation.entity.Item;
import com.graduation.entity.UserOrder;
import com.graduation.service.UserOrderService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    UserOrderService userOrderService;
    @GetMapping("/findOrderDesc/{userId}")
    public ModelAndView findOrderDesc(ModelAndView mav,@PathVariable("userId")int userId)throws Exception{
        System.out.println("根据用户编号查询订单详情，当前用户编号:"+userId);
        List<UserOrder> userOrderList = userOrderService.findOrderAndAddress(userId);
        mav.addObject("orders",userOrderList);//订单信息放在orders 中  当前用户信息，仍然在session中
        mav.setViewName("orderIndex");//跳转到所有订单页面
        return mav;
    }
    //测试查询1002  编号  数据是否能够查询到
    //http://localhost:8080/project1/order/testFindOrderAndAddressJson
    @GetMapping("/testFindOrderAndAddressJson")
    @ResponseBody
    public List<UserOrder> testFindOrderAndAddressJson()throws Exception{
        return userOrderService.findOrderAndAddress(1002);
    }

    //测试查询1000001  1000002 1000003  编号  数据是否能够查询到
    //http://localhost:8080/project1/order/testFindOrderByOrderIdJson/1000001
    @GetMapping("/testFindOrderByOrderIdJson/{orderId}")
    @ResponseBody
    public UserOrder testFindOrderByOrderIdJson(@PathVariable("orderId") int orderId)throws Exception{
        return userOrderService.findOrderAndAddressAndDesc(orderId);
    }


    //根据订单编号，查询订单详情
    //http://localhost:8080/project1/order/findOrderByOrderId/1000003
    @GetMapping("/findOrderByOrderId/{orderId}")
    public ModelAndView findOrderByOrderId(ModelAndView mav,@PathVariable("orderId") int orderId)throws Exception{
        System.out.println("订单编号:"+orderId);
        UserOrder  userOrder= userOrderService.findOrderAndAddressAndDesc(orderId);
        mav.addObject("order",userOrder);//订单信息放在orders 中  当前用户信息，仍然在session中
        mav.setViewName("orderDesc");
        return mav;
    }

    @PostMapping("/buyItem1")
    @ResponseBody
    public String buyItem1(HttpSession session, Item item,int buyCount)throws Exception{
        String result = userOrderService.buyItem1(session,item,buyCount);
        return result;
    }
}
