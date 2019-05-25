package com.ssm.vo;

import lombok.Data;

@Data
public class WeatherVo {
    private String date;
    private String Weather;
    private String temperature;
    private String weaImg;
    private String motionindex;//运动指数

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return Weather;
    }

    public void setWeather(String weather) {
        Weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeaImg() {
        return weaImg;
    }

    public void setWeaImg(String weaImg) {
        this.weaImg = weaImg;
    }

    public String getMotionindex() {
        return motionindex;
    }

    public void setMotionindex(String motionindex) {
        this.motionindex = motionindex;
    }
}
