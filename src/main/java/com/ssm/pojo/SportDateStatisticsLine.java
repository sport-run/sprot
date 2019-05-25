package com.ssm.pojo;

import java.io.Serializable;
import java.util.Date;

public class SportDateStatisticsLine implements Serializable {
    private Integer id;

    private String baiduUid;

    private Date time;

    private Integer parkId;

    private Integer isStart;

    private String image;

    private Date updateTime;

    private Date createTime;

    private Integer status;//1无效

    private static final long serialVersionUID = 1L;

    public SportDateStatisticsLine(Integer id, String baiduUid, Date time, Integer parkId, Integer isStart, String image, Date updateTime,Integer status, Date createTime) {
        this.id = id;
        this.baiduUid = baiduUid;
        this.time = time;
        this.parkId = parkId;
        this.isStart = isStart;
        this.image = image;
        this.updateTime = updateTime;
        this.status=status;
        this.createTime = createTime;
    }

    public SportDateStatisticsLine() {
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
        this.baiduUid = baiduUid == null ? null : baiduUid.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getIsStart() {
        return isStart;
    }

    public void setIsStart(Integer isStart) {
        this.isStart = isStart;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        SportDateStatisticsLine other = (SportDateStatisticsLine) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBaiduUid() == null ? other.getBaiduUid() == null : this.getBaiduUid().equals(other.getBaiduUid()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getParkId() == null ? other.getParkId() == null : this.getParkId().equals(other.getParkId()))
            && (this.getIsStart() == null ? other.getIsStart() == null : this.getIsStart().equals(other.getIsStart()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBaiduUid() == null) ? 0 : getBaiduUid().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getParkId() == null) ? 0 : getParkId().hashCode());
        result = prime * result + ((getIsStart() == null) ? 0 : getIsStart().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}