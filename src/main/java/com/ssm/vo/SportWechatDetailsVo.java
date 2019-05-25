package com.ssm.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class SportWechatDetailsVo {
    private SportDetailItemVo todayItem;
    private SportDetailItemVo weekItem;
    private SportDetailItemVo mothItem;
    private String userimg;//用户头像

    /*private List<Map<String, ArrayList<BigDecimal>>> week;
    private List<Map<String, ArrayList<BigDecimal>>> moth;*/

    public SportDetailItemVo getTodayItem() {
        return todayItem;
    }

    public void setTodayItem(SportDetailItemVo todayItem) {
        this.todayItem = todayItem;
    }

    public SportDetailItemVo getWeekItem() {
        return weekItem;
    }

    public void setWeekItem(SportDetailItemVo weekItem) {
        this.weekItem = weekItem;
    }

    public SportDetailItemVo getMothItem() {
        return mothItem;
    }

    public void setMothItem(SportDetailItemVo mothItem) {
        this.mothItem = mothItem;
    }

/*    public List<Map<String, ArrayList<BigDecimal>>> getWeek() {
        return week;
    }

    public void setWeek(List<Map<String, ArrayList<BigDecimal>>> week) {
        this.week = week;
    }

    public List<Map<String, ArrayList<BigDecimal>>> getMoth() {
        return moth;
    }

    public void setMoth(List<Map<String, ArrayList<BigDecimal>>> moth) {
        this.moth = moth;
    }*/

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }
}
