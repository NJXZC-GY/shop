package com.graduation.service;

import com.graduation.dao.OrderDetailMapper;
import com.graduation.entity.OrderDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Transactional
@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService{

    @Resource
    OrderDetailMapper orderDetailMapper;
    @Override
    public boolean insertOrderDetail(OrderDetail orderDetail) {
        try {
            //注册成功  result 会返回大于0的数值
            int result = orderDetailMapper.insertOrderDetail(orderDetail);
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
