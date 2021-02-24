package com.graduation.entity;

import java.io.Serializable;

public class OrderDetail implements Serializable {

    private int orderDetailId;//ORDER_DETAIL_ID
    private int orderId;//ORDER_ID
    private int itemId;//ITEM_ID
    private int buyCount;//BUY_COUNT

    private Item item;//一个详情只可能对应一个具体商品  一对一关系

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, int orderId, int itemId, int buyCount) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.buyCount = buyCount;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + orderId +
                ", itemId=" + itemId +
                ", buyCount=" + buyCount +
                '}';
    }
}
