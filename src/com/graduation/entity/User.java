package com.graduation.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {

    private int userId;//对应数据库中  USER_ID
    private String userName;//用户名  USER_NAME
    private String pwd;//密码  PWD
    private String sex;//性别  SEX
    private Date birth;//生日 BIRTH
    private int state;//用户状态 STATE
    private int role;//角色 ROLE
    private String userImage;//头像  USER_IMAGE
    private String phone;//电话 PHONE
    private int score;//积分 SCORE
    private double balance;//余额 BALANCE
    //alt+insert
    
    private List<UserOrder> userOrderList;//一个用户可以用多个订单

    public List<UserOrder> getUserOrderList() {
        return userOrderList;
    }

    public void setUserOrderList(List<UserOrder> userOrderList) {
        this.userOrderList = userOrderList;
    }

    public User() {
    }

    public User(int userId, String userName, String pwd, String sex, Date birth, int state, int role, String userImage, String phone, int score, double balance) {
        this.userId = userId;
        this.userName = userName;
        this.pwd = pwd;
        this.sex = sex;
        this.birth = birth;
        this.state = state;
        this.role = role;
        this.userImage = userImage;
        this.phone = phone;
        this.score = score;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", state=" + state +
                ", role=" + role +
                ", userImage='" + userImage + '\'' +
                ", phone='" + phone + '\'' +
                ", score=" + score +
                ", balance=" + balance +
                '}';
    }
}
