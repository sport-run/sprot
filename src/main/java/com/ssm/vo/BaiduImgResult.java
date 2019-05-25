package com.ssm.vo;

import java.io.Serializable;
import java.util.List;

public class BaiduImgResult implements Serializable {

    /**
     *
     */
    private String  face_token;

    private List<BaiduGroupInfo> user_list;

    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }

    public List<BaiduGroupInfo> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<BaiduGroupInfo> user_list) {
        this.user_list = user_list;
    }
}
