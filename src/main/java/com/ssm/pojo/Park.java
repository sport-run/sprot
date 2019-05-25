package com.ssm.pojo;

import java.io.Serializable;
import java.util.Date;

public class Park implements Serializable {
    private Integer id;

    private String name;

    private Long perimeter;

    private String cameraStart;

    private String cameraEnd;

    private String cameraOther;

    private Long parkGrade;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Park(Integer id, String name, Long perimeter, String cameraStart, String cameraEnd, String cameraOther, Long parkGrade, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.perimeter = perimeter;
        this.cameraStart = cameraStart;
        this.cameraEnd = cameraEnd;
        this.cameraOther = cameraOther;
        this.parkGrade = parkGrade;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Park() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Long perimeter) {
        this.perimeter = perimeter;
    }

    public String getCameraStart() {
        return cameraStart;
    }

    public void setCameraStart(String cameraStart) {
        this.cameraStart = cameraStart == null ? null : cameraStart.trim();
    }

    public String getCameraEnd() {
        return cameraEnd;
    }

    public void setCameraEnd(String cameraEnd) {
        this.cameraEnd = cameraEnd == null ? null : cameraEnd.trim();
    }

    public String getCameraOther() {
        return cameraOther;
    }

    public void setCameraOther(String cameraOther) {
        this.cameraOther = cameraOther == null ? null : cameraOther.trim();
    }

    public Long getParkGrade() {
        return parkGrade;
    }

    public void setParkGrade(Long parkGrade) {
        this.parkGrade = parkGrade;
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
        Park other = (Park) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPerimeter() == null ? other.getPerimeter() == null : this.getPerimeter().equals(other.getPerimeter()))
            && (this.getCameraStart() == null ? other.getCameraStart() == null : this.getCameraStart().equals(other.getCameraStart()))
            && (this.getCameraEnd() == null ? other.getCameraEnd() == null : this.getCameraEnd().equals(other.getCameraEnd()))
            && (this.getCameraOther() == null ? other.getCameraOther() == null : this.getCameraOther().equals(other.getCameraOther()))
            && (this.getParkGrade() == null ? other.getParkGrade() == null : this.getParkGrade().equals(other.getParkGrade()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPerimeter() == null) ? 0 : getPerimeter().hashCode());
        result = prime * result + ((getCameraStart() == null) ? 0 : getCameraStart().hashCode());
        result = prime * result + ((getCameraEnd() == null) ? 0 : getCameraEnd().hashCode());
        result = prime * result + ((getCameraOther() == null) ? 0 : getCameraOther().hashCode());
        result = prime * result + ((getParkGrade() == null) ? 0 : getParkGrade().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}