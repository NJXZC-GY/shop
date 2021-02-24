package com.graduation.controller;

import com.graduation.entity.Item;
import com.graduation.entity.User;
import com.graduation.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class TestController {

    @Autowired
    ItemService itemService;

    /*当你访问 :    http://localhost:8080/toIndex    可以访问到 templates下 index.html     */
    @RequestMapping("/toIndex")
    public String toIndex(HttpServletRequest request)throws Exception{
        System.out.println("上下文路径:"+request.getContextPath());
        return "index";
    }

    @RequestMapping("/toSignIn")
    public String toSignIn()throws Exception{
        return "signin";
    }

    @RequestMapping("/toIndexMav")
    public ModelAndView toIndexMav(ModelAndView mav)throws Exception{

        mav.setViewName("index");//指定要跳转到的页面

        mav.addObject("name","刘");//可以是任意内容，例如:对象，字符串,集合等
        //通过mav  传递 对象
        //public User(int userId, String userName, String pwd, String sex, Date birth, int state,
        // int role, String userImage, String phone, int score, double balance)
        User user = new User(1001,"小马","123","男",new Date(),1,9,"p1.jpeg",
                "13877723569",500,775.7);
        mav.addObject("user",user);//注意  第二个user  没有 ""
        mav.addObject("info",user);
        //多个对象组成的集合
        User tom = new User(1002,"tom","123","男",new Date(),1,9,"p2.jpeg",
                "13822222222",6,7.77);
        User jack = new User(1003,"jack","123","女",new Date(),0,1,"p3.jpeg",
                "13811111111",1500,66.7);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(jack);
        users.add(tom);
        mav.addObject("users",users);
        return mav;
    }
    /*文件上传  单文件*/
    @PostMapping("/upload1")
    @ResponseBody
    public String upload1(MultipartFile pic1,User user,String userName)throws Exception{
        System.out.println("获取到的User:"+user.toString());
        System.out.println("获取到的userName:"+userName);
        if (pic1.isEmpty()){
            System.out.println("未获取到文件");
            return "fileMissing！";
        }else{
            String fileName= pic1.getOriginalFilename();
            System.out.println("文件名:"+fileName);
            String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取文件后缀
            //根据不同后缀 将不同类型文件上传到 不同文件夹（前端控制）
            System.out.println("文件后缀:"+suffixName);
            //文件保存地址   最后一定要添加  //
            String filePath = "D://upload_2021//project1//image//";
            //文件名重构  考虑到文件同名的问题，这里最好将文件名通过 UUID 重新处理
            fileName = UUID.randomUUID()+suffixName;//这样可以保证生成的文件名 不重复
            File dest = new File(filePath+fileName);//生成文件地址
            //目录不存在则创建
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            try {
                pic1.transferTo(dest);
                return "uploadSuccess";
            }catch (Exception e){
                System.out.println(e);
                return "uploadFail";
            }
        }
    }
    /*多文件上传*/
    @PostMapping("/upload2")
    @ResponseBody
    public String upload2(MultipartFile[] files)throws Exception{
        System.out.println("获取到的文件数量:"+files.length);

        for(int i=0;i<files.length;i++){
            if(files[i].isEmpty()){
                System.out.println("未获取到文件！:位置:"+i);

            }else{
                String fileName= files[i].getOriginalFilename();
                System.out.println("文件名:"+fileName);
                String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取文件后缀
                //根据不同后缀 将不同类型文件上传到 不同文件夹（前端控制）
                System.out.println("文件后缀:"+suffixName);
                //文件保存地址   最后一定要添加  //
                String filePath = "D://upload_2021//project1//image//";
                fileName = UUID.randomUUID()+suffixName;//这样可以保证生成的文件名 不重复
                File dest = new File(filePath+fileName);//生成文件地址
                //目录不存在则创建
                if(!dest.getParentFile().exists()){
                    dest.getParentFile().mkdirs();
                }
                try {
                    files[i].transferTo(dest);
                }catch (Exception e){
                    System.out.println(e);
                    return "uploadFail";
                }
            }
        }

        return "uploadSuccess";
    }

    //不登录 也可以跳转到商品主页
    //http://localhost:8080/project1/toItemIndex
    @GetMapping("/toItemIndex")
    public ModelAndView toItemIndex(ModelAndView mav)throws Exception{
        Item item = new Item();
        item.setItemState(1);//我们设计的  商品状态为1  是上架商品
        System.out.println("查询条件:"+item.toString());
        List<Item> allItems = itemService.findAllItemsByCondition(item);
        List<Item> heartItems = itemService.findItemsByItemHeart();
        mav.setViewName("itemIndex");//设置跳转页面
        mav.addObject("allItems",allItems);//将查询到的所有商品 放入Request域中
        mav.addObject("heartItems",heartItems);//将查询到的热销商品 放入Request域中
        return mav;
    }
}
