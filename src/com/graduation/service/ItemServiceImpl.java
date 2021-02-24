package com.graduation.service;

import com.graduation.dao.ItemMapper;
import com.graduation.entity.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service("itemService")
public class ItemServiceImpl implements ItemService{

    @Resource
    ItemMapper itemMapper;
    @Override
    public List<Item> findAllItems() {
        System.out.println("查询所有商品");
        return itemMapper.findAllItems();
    }

    @Override
    public List<Item> findItemsByItemHeart() {
        System.out.println("根据热度排序查询");
        return itemMapper.findItemsByItemHeart();
    }

    @Override
    public Item findItemByItemId(int itemId) {
        System.out.println("根据商品编号查询商品业务逻辑");
        return  itemMapper.findItemByItemId(itemId);
    }
    //将数据库中 ItemImageOther  数据为   localhost:8080/project1/image/p1.jpg localhost:8080/project1/image/p2.jpg
    //改造成   List<String>    数据结构  改为["localhost:8080/project1/image/p1.jpg","localhost:8080/project1/image/p2.jpg"]
    //可以通过   urlList[0] 获取到localhost:8080/project1/image/p1.jpg
    //          urlList[1] 获取到localhost:8080/project1/image/p2.jpg
    @Override
    public List<String> dealItemImageOther(String itemImageOther) {
        if(null==itemImageOther){
            return null;
        }else{
            itemImageOther = itemImageOther.trim();//去除前后的空格
            String imageUrls[] = itemImageOther.split(" ");//将String 字符串解析为数组
            List<String> urlList = new ArrayList<>();
            for(String url:imageUrls){
                urlList.add(url);
            }
            return urlList;
        }

    }

    @Override
    public boolean addItem(Item item) {
        try {
            //注册成功  result 会返回大于0的数值
            int result = itemMapper.addItem(item);
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

    @Override
    public boolean deleteItem(int itemId) {
        try {
            //注册成功  result 会返回大于0的数值
            int result = itemMapper.deleteItem(itemId);
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

    @Override
    public List<Item> findAllItemsByCondition(Item item) {
        System.out.println("根据条件查询商品");
        return itemMapper.findAllItemsByCondition(item);
    }

    /*商品如果原本是上架状态，点击后变为下架，    原本为下架状态，点击后则变为上架 */
    @Override
    public boolean updateItemState(Item item) {
        //获取到原始状态是1  （上架）   直接修改为2（下架）
        if(1==item.getItemState()){
            item.setItemState(2);
        }else{//原先为下架 则修改为1 （上架）
            item.setItemState(1);
        }
        try {
            //注册成功  result 会返回大于0的数值
            int result = itemMapper.updateItemStateByItemId(item);
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

    @Override
    public boolean updateItem(Item item) {
        try {
            //注册成功  result 会返回大于0的数值
            int result = itemMapper.updateItemByItemId(item);
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

    @Override
    public List<Item> findItemsByKey(String key) {
        System.out.println("根据关键字查询");
        return itemMapper.findItemsByKey(key);
    }

    @Override
    public List<Item> findItemsByKey2(String key) {
        System.out.println("根据关键字查询2");
        return itemMapper.findItemsByKey2(key);
    }
}
