package com.ssm.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SportData implements Serializable {
    private Integer id;

    private String uid;

    private String baiduUid;

    private BigDecimal distance;

    private Date date;

    private Long sportTime;

    private BigDecimal maxSpeed;

    private BigDecimal averageTime;//平均

    private BigDecimal energyMetabolism;

    private BigDecimal energyExpenditure;
    private BigDecimal  cyclenumer;
    private BigDecimal  chickenleg;

    private Date createTime;

    private Date updateTime;

    private String image;

    private static final long serialVersionUID = 1L;

    public SportData(Integer id, String uid, String baiduUid, BigDecimal distance, Date date, Long sportTime, BigDecimal maxSpeed, BigDecimal energyMetabolism, BigDecimal energyExpenditure, Date createTime, Date updateTime) {
        this.id = id;
        this.uid = uid;
        this.baiduUid = baiduUid;
        this.distance = distance;
        this.date = date;
        this.sportTime = sportTime;
        this.maxSpeed = maxSpeed;
        this.energyMetabolism = energyMetabolism;
        this.energyExpenditure = energyExpenditure;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public SportData() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBaiduUid() {
        return baiduUid;
    }

    public void setBaiduUid(String baiduUid) {
        this.baiduUid = baiduUid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getSportTime() {
        return sportTime;
    }

    public void setSportTime(Long sportTime) {
        this.sportTime = sportTime;
    }

    public BigDecimal getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(BigDecimal maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getEnergyMetabolism() {
        return energyMetabolism;
    }

    public void setEnergyMetabolism(BigDecimal energyMetabolism) {
        this.energyMetabolism = energyMetabolism;
    }

    public BigDecimal getEnergyExpenditure() {
        return energyExpenditure;
    }

    public void setEnergyExpenditure(BigDecimal energyExpenditure) {
        this.energyExpenditure = energyExpenditure;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getCyclenumer() {
        return cyclenumer;
    }

    public void setCyclenumer(BigDecimal cyclenumer) {
        this.cyclenumer = cyclenumer;
    }

    public BigDecimal getChickenleg() {
        return chickenleg;
    }

    public void setChickenleg(BigDecimal chickenleg) {
        this.chickenleg = chickenleg;
    }

    public BigDecimal getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(BigDecimal averageTime) {
        this.averageTime = averageTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SportData other = (SportData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getDistance() == null ? other.getDistance() == null : this.getDistance().equals(other.getDistance()))
                && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
                && (this.getSportTime() == null ? other.getSportTime() == null : this.getSportTime().equals(other.getSportTime()))
                && (this.getMaxSpeed() == null ? other.getMaxSpeed() == null : this.getMaxSpeed().equals(other.getMaxSpeed()))
                && (this.getEnergyMetabolism() == null ? other.getEnergyMetabolism() == null : this.getEnergyMetabolism().equals(other.getEnergyMetabolism()))
                && (this.getEnergyExpenditure() == null ? other.getEnergyExpenditure() == null : this.getEnergyExpenditure().equals(other.getEnergyExpenditure()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getDistance() == null) ? 0 : getDistance().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getSportTime() == null) ? 0 : getSportTime().hashCode());
        result = prime * result + ((getMaxSpeed() == null) ? 0 : getMaxSpeed().hashCode());
        result = prime * result + ((getEnergyMetabolism() == null) ? 0 : getEnergyMetabolism().hashCode());
        result = prime * result + ((getEnergyExpenditure() == null) ? 0 : getEnergyExpenditure().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}