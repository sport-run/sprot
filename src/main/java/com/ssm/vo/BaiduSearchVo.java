package com.ssm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class BaiduSearchVo {

    private int error_code;
    private String error_msg;
    private long  log_id;
    private long timestamp;
    private int cached;
    private BaiduImgResult result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCached() {
        return cached;
    }

    public void setCached(int cached) {
        this.cached = cached;
    }

    public BaiduImgResult getResult() {
        return result;
    }

    public void setResult(BaiduImgResult result) {
        this.result = result;
    }
    /*@JsonProperty("face_token")
    private String  faceToken;
    @JsonProperty("user_list")
    List<BaiduUser> userList;

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public List<BaiduUser> getUserList() {
        return userList;
    }

    public void setUserList(List<BaiduUser> userList) {
        this.userList = userList;
    }

    @Data
    public class BaiduUser{
        @JsonProperty("group_id")
        private String groupId;
        @JsonProperty("user_id")
        private String userId;
        private double score;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }*/
}
