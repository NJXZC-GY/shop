package com.graduation.dao;

import com.graduation.entity.Address;

public interface AddressMapper {
    //根据用户编号查询该用户默认地址
    Address findDefaultAddressByUserId(int userId);
}

