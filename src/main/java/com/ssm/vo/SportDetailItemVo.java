package com.ssm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SportDetailItemVo {
    //平均速度
    private BigDecimal speed=new BigDecimal(0);
    //运动频率
    private String rate="";
    //运动习惯
    private String habits="";
    //总时长
    private long time;
    //总里程
    private BigDecimal distance=new BigDecimal(0);;
    //消耗热量
    @JsonProperty("energy_expenditure")
    private BigDecimal energyExpenditure=new BigDecimal(0);;
    //能量代谢
    @JsonProperty("energy_metabolism")
    private BigDecimal energyMetabolism=new BigDecimal(0);;
    //圈数
    private Integer cyclenumer=0;
    //鸡腿
    private int chickenleg=0;

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getEnergyExpenditure() {
        return energyExpenditure;
    }

    public void setEnergyExpenditure(BigDecimal energyExpenditure) {
        this.energyExpenditure = energyExpenditure;
    }

    public BigDecimal getEnergyMetabolism() {
        return energyMetabolism;
    }

    public void setEnergyMetabolism(BigDecimal energyMetabolism) {
        this.energyMetabolism = energyMetabolism;
    }

    public Integer getCyclenumer() {
        return cyclenumer;
    }

    public void setCyclenumer(Integer cyclenumer) {
        this.cyclenumer = cyclenumer;
    }

    public int getChickenleg() {
        return chickenleg;
    }

    public void setChickenleg(int chickenleg) {
        this.chickenleg = chickenleg;
    }
}
