package com.graduation.controller;

import com.graduation.entity.Item;
import com.graduation.service.ItemService;
import com.graduation.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    ItemService itemService;
    //访问地址:  http://localhost:8080/project1/item/toItemDesc/100001
    ///toItemDesc/{itemId}   地址中   itemId 可以通过@PathVariable("itemId")int itemId  进行绑定     restful  URL
    @GetMapping("/toItemDesc/{itemId}")
    public ModelAndView toItemDesc(ModelAndView mav, @PathVariable("itemId")int itemId)throws Exception{
        //1.查询商品信息
        Item item = itemService.findItemByItemId(itemId);
        //2.解析其他多个图片
        List<String> urlList = itemService.dealItemImageOther(item.getItemImageOther());

        mav.addObject("item",item);
        mav.addObject("urlList",urlList);
        mav.setViewName("itemDesc");
        return mav;
    }
    //跳转到商品上架页面
    @GetMapping("/toAddItem")
    public String toAddItem()throws Exception{
        return "addItem";
    }

    @PostMapping("/addItem")
    public ModelAndView addItem(ModelAndView mav,Item item)throws Exception{
        System.out.println("获取到的Item:"+item);
        //1.调用ItemService中  注册商品业务逻辑  进行商品注册
        boolean result = itemService.addItem(item);
        //2.ItemMapper 编写  insert 方法
        if (result){
            mav.addObject("addMsg","上架成功！");
        }else{
            mav.addObject("addMsg","上架失败！");

        }
        mav.setViewName("addItemResult");
        return mav;
    }

    @PostMapping("/addItem2")
    public ModelAndView addItem2(ModelAndView mav, Item item, MultipartFile imageMainFile, HttpServletRequest request)
            throws Exception{
        System.out.println("获取到的Item:"+item);
        String imageMainUrl = UploadUtil.uploadOneFile(imageMainFile,"D://upload_2021//project1//",request,"item");
        item.setItemImageMain(imageMainUrl);
        //1.调用ItemService中  注册商品业务逻辑  进行商品注册
        boolean result = itemService.addItem(item);
        //2.ItemMapper 编写  insert 方法
        if (result){
            mav.addObject("addMsg","上架成功！");
        }else{
            mav.addObject("addMsg","上架失败！");

        }
        mav.setViewName("addItemResult");
        return mav;
    }

    @PostMapping("/addItem3")
    public ModelAndView addItem3(ModelAndView mav, Item item, MultipartFile imageMainFile, HttpServletRequest request,
                                 MultipartFile[] imageOtherFiles)
            throws Exception{
        System.out.println("获取到的Item:"+item);
        //处理单个图片
        String imageMainUrl = UploadUtil.uploadOneFile(imageMainFile,"E://upload_2021//project1//",request,"item");
        item.setItemImageMain(imageMainUrl);
        //处理多个图片
        String imageOtherUrl = UploadUtil.uploadMutiFile(imageOtherFiles,"E://upload_2021//project1//",request,"item");
        item.setItemImageOther(imageOtherUrl);
        //1.调用ItemService中  注册商品业务逻辑  进行商品注册
        boolean result = itemService.addItem(item);
        //2.ItemMapper 编写  insert 方法
        if (result){
            mav.addObject("addMsg","上架成功！");
        }else{
            mav.addObject("addMsg","上架失败！");

        }
        mav.setViewName("addItemResult");
        return mav;
    }

    @GetMapping("/deleteItem/{itemId}")
    public ModelAndView deleteItem(ModelAndView mav,@PathVariable("itemId")int itemId)throws Exception{
        //1.根据商品编号 删除商品
        boolean result = itemService.deleteItem(itemId);
        if(result){
            System.out.println("删除成功！");
            mav.addObject("deleteInfo","删除成功！");
        }else{
            System.out.println("删除失败！");
            mav.addObject("deleteInfo","删除失败！");

        }
        //2.删除后想要跳转回商品主页，注意  一定要查询数据
        //该段是普通用户登陆之后的效果，登陆后要能看到，登陆后才能看的内容，可能是商品、电影、具体信息
        //能够查询出该用户有权限看到的信息（商品为例，可以看到按种类区分的商品、按照热度排名的商品、可能是各种各样的）
        List<Item> allItems = itemService.findAllItems();
        List<Item> heartItems = itemService.findItemsByItemHeart();
        mav.setViewName("itemIndex");//设置跳转页面
        mav.addObject("allItems",allItems);//将查询到的所有商品 放入Request域中
        mav.addObject("heartItems",heartItems);//将查询到的热销商品 放入Request域中
        return mav;
    }
    //商品上架、下架状态切换方法
    @GetMapping("updateItemState/{itemId}/{itemState}")
    public ModelAndView updateItemState(ModelAndView mav,@PathVariable("itemId")int itemId,
                                        @PathVariable("itemState")int itemState)throws Exception{
        Item item = new Item();
        item.setItemId(itemId);//将得到的编号放入Item对象
        item.setItemState(itemState);//将得到的原始状态放入Item对象
        System.out.println("Id与原始State:"+item.toString());
        //1.调用修改方法
        boolean result = itemService.updateItemState(item);
        if(result){
            System.out.println("修改成功！");
            mav.addObject("updateStateInfo","修改成功！");
        }else{
            System.out.println("修改失败！");
            mav.addObject("updateStateInfo","修改失败！");

        }
        List<Item> allItems = itemService.findAllItems();
        List<Item> heartItems = itemService.findItemsByItemHeart();
        mav.setViewName("itemIndex");//设置跳转页面
        mav.addObject("allItems",allItems);//将查询到的所有商品 放入Request域中
        mav.addObject("heartItems",heartItems);//将查询到的热销商品 放入Request域中
        return mav;
    }
    //跳转到商品修改页面  首先要将能够修改的信息，显示在页面中
    @GetMapping("/toUpdateItem/{itemId}")
    public ModelAndView toUpdateItem(ModelAndView mav, @PathVariable("itemId")int itemId)throws Exception{
        //1.查询商品信息
        Item item = itemService.findItemByItemId(itemId);
        //2.解析其他多个图片
        List<String> urlList = itemService.dealItemImageOther(item.getItemImageOther());

        mav.addObject("item",item);
        mav.addObject("urlList",urlList);
        mav.setViewName("itemUpdate");
        return mav;
    }

    @PostMapping("/updateItemOne")
    public ModelAndView updateItemOne(ModelAndView mav, Item item, MultipartFile imageMainFile,
                                      HttpServletRequest request, MultipartFile[] imageOtherFiles,
                                      String[] otherImageOriginalUrl){
        //将图片地址 与 上传文件结合  重新处理上传的图片 如果原地址有内容则替换URL  如果没有内容则
        String imageOtherUrl = UploadUtil.uploadMutiFileUpdate(imageOtherFiles,
                "E://upload_2021//project1//",
                request,"item",otherImageOriginalUrl);
        System.out.println("更新后的："+imageOtherUrl);//工具类处理完成

        //仍需处理主图片更新，方式与其他图片处理基本一致


        return mav;
    }


    @PostMapping("/updateItemTest")
    @ResponseBody
    public String updateItemTest(Item item,MultipartFile imageMainFile,MultipartFile[] imageOtherFiles,
                                 HttpServletRequest request,String[] otherImageOriginalUrl,
                                 String mainImageOriginalUrl)throws
            Exception{
        System.out.println("前台传递的Item:"+item.toString());
        String imageOtherUrl = UploadUtil.uploadMutiFileUpdate(imageOtherFiles,
                "E://upload_2021//project1//",
                request,"item",otherImageOriginalUrl);
        System.out.println("更新后的："+imageOtherUrl);//工具类处理完成
        String imageMainUrl = UploadUtil.uploadOneFileUpdate(imageMainFile,
                "E://upload_2021//project1//",request,"item",mainImageOriginalUrl);
        System.out.println("更新后的主图片："+imageMainUrl);//工具类处理完成
        //将处理好的其他图片地址、主图片地址、上传视频地址（未处理，自行完成）  放入Item 对象
        item.setItemImageMain(imageMainUrl);
        item.setItemImageOther(imageOtherUrl);
        boolean result = itemService.updateItem(item);
        if (result){
            return "updateSuccess";
        }else{
            return "updateFail";
        }
    }
    //根据关键字查询商品
    @PostMapping("/searchItemBykey")
    public ModelAndView searchItemBykey(ModelAndView mav,String key)throws Exception{

        //调用根据关键字查询商品的方法
        //List<Item> allItems = itemService.findItemsByKey(key);
        List<Item> allItems = itemService.findItemsByKey2(key);
        List<Item> heartItems = itemService.findItemsByItemHeart();
        mav.setViewName("itemIndex");//设置跳转页面
        mav.addObject("allItems",allItems);//将查询到的所有商品 放入Request域中
        mav.addObject("heartItems",heartItems);//将查询到的热销商品 放入Request域中
        return mav;
    }
}
