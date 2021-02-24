package com.graduation.entity;

import java.io.Serializable;

public class ItemType implements Serializable {

    private int itemTypeId;
    private String typeName;
    private String typeDesc;

    public ItemType() {
    }

    public int getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(int itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public ItemType(int itemTypeId, String typeName, String typeDesc) {
        this.itemTypeId = itemTypeId;
        this.typeName = typeName;
        this.typeDesc = typeDesc;
    }

    @Override
    public String toString() {
        return "ItemType{" +
                "itemTypeId=" + itemTypeId +
                ", typeName='" + typeName + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                '}';
    }
}
