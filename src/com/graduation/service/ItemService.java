package com.graduation.service;

import com.graduation.entity.Item;

import java.util.List;

public interface ItemService {
    //查询所有商品业务逻辑
    List<Item> findAllItems();
    //根据热度查询商品业务逻辑
    List<Item> findItemsByItemHeart();
    //根据商品编号查商品业务逻辑
    Item findItemByItemId(int itemId);
    //解析图片
    List<String> dealItemImageOther(String itemImageOther);
    //商品上架
    boolean addItem(Item item);
    //删除商品
    boolean deleteItem(int itemId);
    //根据条件查询商品
    List<Item> findAllItemsByCondition(Item item);
    //修改商品状态
    boolean updateItemState(Item item);
    //修改商品（根据录入的条件不同，修改不同选项）
    boolean updateItem(Item item);
    //根据关键字 查询商品信息
    List<Item> findItemsByKey(String key);
    //根据关键字 查询商品信息2
    List<Item> findItemsByKey2(String key);
}
