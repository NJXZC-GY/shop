package com.graduation.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class UserOrder implements Serializable {

    private int orderId;//ORDER_ID
    private int userId;//USER_ID
    private int addressId;//ADDRESS_ID
    private Timestamp orderTime;//ORDER_TIME
    private Timestamp getTime;//GET_TIME
    private double total;//TOTAL
    private String note;//NOTE

    private Address address;//一个订单只可能有一个地址  一对一
    private List<OrderDetail> orderDetailList;//一个订单可能会有多条详情请记录  一对多

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public UserOrder() {
    }

    public UserOrder(int orderId, int userId, int addressId, Timestamp orderTime, Timestamp getTime, double total, String note) {
        this.orderId = orderId;
        this.userId = userId;
        this.addressId = addressId;
        this.orderTime = orderTime;
        this.getTime = getTime;
        this.total = total;
        this.note = note;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Timestamp getGetTime() {
        return getTime;
    }

    public void setGetTime(Timestamp getTime) {
        this.getTime = getTime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", addressId=" + addressId +
                ", orderTime=" + orderTime +
                ", getTime=" + getTime +
                ", total=" + total +
                ", note='" + note + '\'' +
                '}';
    }
}
