package com.graduation.entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;


public class Item implements Serializable {

    private int itemId; //ITEM_ID
    private String itemName; //ITEM_NAME
    private double itemPrice; //ITEM_PRICE
    private int itemCount; //ITEM_COUNT
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp itemCreateTime; //ITEM_CREATE_TIME
    private String itemDesc; //ITEM_DESC
    private int itemTypeId; //ITEM_TYPE_ID
    private double itemSalePrice; //ITEM_SALE_PRICE
    private int itemState; //ITEM_STATE
    private String itemImageMain; //ITEM_IMAGE_MAIN
    private String itemImageOther; //ITEM_IMAGE_OTHER
    private String itemVideoUrl; //ITEM_VIDEO_URL
    private int itemHeart;//ITEM_HEART

    private ItemType itemType;//一对一映射   一个商品只可能属于一个种类

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Item() {
    }

    public Item(int itemId, String itemName, double itemPrice, int itemCount, Timestamp itemCreateTime, String itemDesc, int itemTypeId, double itemSalePrice, int itemState, String itemImageMain, String itemImageOther, String itemVideoUrl, int itemHeart) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.itemCreateTime = itemCreateTime;
        this.itemDesc = itemDesc;
        this.itemTypeId = itemTypeId;
        this.itemSalePrice = itemSalePrice;
        this.itemState = itemState;
        this.itemImageMain = itemImageMain;
        this.itemImageOther = itemImageOther;
        this.itemVideoUrl = itemVideoUrl;
        this.itemHeart = itemHeart;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public Timestamp getItemCreateTime() {
        return itemCreateTime;
    }

    public void setItemCreateTime(Timestamp itemCreateTime) {
        this.itemCreateTime = itemCreateTime;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(int itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public double getItemSalePrice() {
        return itemSalePrice;
    }

    public void setItemSalePrice(double itemSalePrice) {
        this.itemSalePrice = itemSalePrice;
    }

    public int getItemState() {
        return itemState;
    }

    public void setItemState(int itemState) {
        this.itemState = itemState;
    }

    public String getItemImageMain() {
        return itemImageMain;
    }

    public void setItemImageMain(String itemImageMain) {
        this.itemImageMain = itemImageMain;
    }

    public String getItemImageOther() {
        return itemImageOther;
    }

    public void setItemImageOther(String itemImageOther) {
        this.itemImageOther = itemImageOther;
    }

    public String getItemVideoUrl() {
        return itemVideoUrl;
    }

    public void setItemVideoUrl(String itemVideoUrl) {
        this.itemVideoUrl = itemVideoUrl;
    }

    public int getItemHeart() {
        return itemHeart;
    }

    public void setItemHeart(int itemHeart) {
        this.itemHeart = itemHeart;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemCount=" + itemCount +
                ", itemCreateTime=" + itemCreateTime +
                ", itemDesc='" + itemDesc + '\'' +
                ", itemTypeId=" + itemTypeId +
                ", itemSalePrice=" + itemSalePrice +
                ", itemState=" + itemState +
                ", itemImageMain='" + itemImageMain + '\'' +
                ", itemImageOther='" + itemImageOther + '\'' +
                ", itemVideoUrl='" + itemVideoUrl + '\'' +
                ", itemHeart=" + itemHeart +
                '}';
    }
}
