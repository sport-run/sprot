package com.ssm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BaiduAddVo {


    private int error_code;
    private String  error_msg;
    private Long  log_id;
    private Long timestamp;
    private int cached;

    private BaiduAddface result;

    @Data
    public class BaiduAddface{
        @JsonProperty("face_token")
        private String faceToken;

        public String getFaceToken() {
            return faceToken;
        }

        public void setFaceToken(String faceToken) {
            this.faceToken = faceToken;
        }
    }

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

    public Long getLog_id() {
        return log_id;
    }

    public void setLog_id(Long log_id) {
        this.log_id = log_id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCached() {
        return cached;
    }

    public void setCached(int cached) {
        this.cached = cached;
    }

    public BaiduAddface getResult() {
        return result;
    }

    public void setResult(BaiduAddface result) {
        this.result = result;
    }
}
