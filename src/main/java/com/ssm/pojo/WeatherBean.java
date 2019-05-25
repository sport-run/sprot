package com.ssm.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherBean {
    private String cityid;
    //	气象台更新时间
    @JsonProperty("update_time")
    private String updateTime;
    //日期
    private String date;
    //星期
    private String week;
    //	天气情况
    private String wea;
    //	天气对应图标
    @JsonProperty("wea_img")
    private String weaImg;
    //当前温度
    private String tem;
    //空气质量
    private String air;
    //PM2.5
    @JsonProperty("air_pm25")
    private String airPm25;
    //	空气质量等级
    @JsonProperty("air_level")
    private String airLevel;
    //空气质量描述
    @JsonProperty("air_tips")
    private String airTips;
    //湿度
    private String humidity;
    //能见度
    private String visibility;
    //气压hPa
    private String pressure;
    //风向
    private String win;
    //风速等级
    @JsonProperty("win_speed")
    private String winSpeed;
    //风速 如: 12km/h
    @JsonProperty("win_meter")
    private String winMeter;

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getWeaImg() {
        return weaImg;
    }

    public void setWeaImg(String weaImg) {
        this.weaImg = weaImg;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getAirPm25() {
        return airPm25;
    }

    public void setAirPm25(String airPm25) {
        this.airPm25 = airPm25;
    }

    public String getAirLevel() {
        return airLevel;
    }

    public void setAirLevel(String airLevel) {
        this.airLevel = airLevel;
    }

    public String getAirTips() {
        return airTips;
    }

    public void setAirTips(String airTips) {
        this.airTips = airTips;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getWinSpeed() {
        return winSpeed;
    }

    public void setWinSpeed(String winSpeed) {
        this.winSpeed = winSpeed;
    }

    public String getWinMeter() {
        return winMeter;
    }

    public void setWinMeter(String winMeter) {
        this.winMeter = winMeter;
    }
}
