package com.ssm.vo;

import lombok.Data;

@Data
public class SportListItemVo {
    private String userImage;
    private long distance;

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}
