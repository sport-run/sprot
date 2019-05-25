package com.ssm.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class User implements Serializable {
    private Integer uid;

    private String baiduUid;

    private String username;

    private Integer state;

    private Integer weight;

    private Integer age;

    private Integer height;

    private String phone;

    private Integer gender;

    private String image;

    private String openid;//微信opneid

    private Date registerTime;//注册时间

    private static final long serialVersionUID = 1L;


    public User() {
        super();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getBaiduUid() {
        return baiduUid;
    }

    public void setBaiduUid(String baiduUid) {
        this.baiduUid = baiduUid == null ? null : baiduUid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }


}