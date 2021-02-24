package com.graduation.service;

import com.graduation.dao.AddressMapper;
import com.graduation.dao.UserOrderMapper;
import com.graduation.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Transactional
@Service("userOrderService")
public class UserOrderServiceImpl implements UserOrderService{

    @Resource
    UserOrderMapper userOrderMapper;
    @Resource
    AddressMapper addressMapper;
    @Resource
    OrderDetailService orderDetailService;
    @Override
    public List<UserOrder> findOrderAndAddress(int userId) {
        return userOrderMapper.findOrderAndAddress(userId);
    }

    @Override
    public UserOrder findOrderAndAddressAndDesc(int orderId) {
        return userOrderMapper.findOrderAndAddressAndDesc(orderId);
    }

    @Override
    public String buyItem1(HttpSession session, Item item,int buyCount) {
        if ((null!=session.getAttribute("loginUser"))){
            User user = (User) session.getAttribute("loginUser");
            double total = buyCount*item.getItemSalePrice();
            if(user.getBalance()>=total){
                //1.订单编号如何处理（订单表生成后，需要把订单编号再次添加到订单详情表中）
                int orderId = (int)(Math.random()*1234567890);
                //订单编号使用随机生成  用户编号session获取   地址编号应该用当前用户的默认地址（暂未处理）
                //订单时间使用当前系统时间时间戳   收货时间null
                //总价其实可以计算使用buyCount*item.getItemSalePrice();    备注使用默认值 直接购买
                //处理默认地址
                int addressId = addressMapper.findDefaultAddressByUserId(user.getUserId()).getAddressId();
                UserOrder userOrder = new UserOrder(orderId,user.getUserId(),addressId,
                        new Timestamp(System.currentTimeMillis()),null,total,"直接购买");
                //调用插入方法   将订单信息录入数据库
                boolean b = insertUserOrder(userOrder);
                if(b){
                    //订单生成成功后，再将商品详情记录到 订单详情中
                    OrderDetail orderDetail = new OrderDetail(0,orderId,item.getItemId(),buyCount);
                    boolean addDetailFlag = orderDetailService.insertOrderDetail(orderDetail);
                    if(addDetailFlag){
                        return "success";
                    }else{
                        return "orderDetailFail";//订单详情失败
                    }
                }else{
                    return "userOrderFail1";//订单录入失败
                }


            }else{
                return "noMoney";//购买时，钱不够
            }
        }else{
            return "noLogin";//如果session中没有用户信息，未登录
        }

    }

    @Override
    public boolean insertUserOrder(UserOrder userOrder) {
        try {
            //注册成功  result 会返回大于0的数值
            int result = userOrderMapper.insertUserOrder(userOrder);
            System.out.println("操作数据数:"+result);
            if (result>0){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return false;
    }
}
