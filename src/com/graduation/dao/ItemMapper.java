package com.graduation.dao;

import com.graduation.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper {
    //查询所有商品
    List<Item> findAllItems();
    //根据商品热度排序查询
    List<Item> findItemsByItemHeart();
    //根据商品编号查询商品
    Item findItemByItemId(int itemId);
    //商品注册方法
    int addItem(Item item);
    //删除商品
    int deleteItem(int itemId);
    //根据用户输入条件，查询商品
    List<Item> findAllItemsByCondition(Item item);
    //商品上架（根据商品编号，修改商品状态）
    int updateItemStateByItemId(Item item);
    //根据输入内容修改商品信息
    int updateItemByItemId(Item item);
    //根据关键字查询商品   自定义参数  在Mapper.xml中 可以通过 key  获取到我们需要的内容
    List<Item> findItemsByKey(@Param("key") String key);
    //商品类别 通过多表查询进行处理
    List<Item> findItemsByKey2(@Param("key") String key);
}
