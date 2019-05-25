package com.ssm.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CameraParam {
            private int type;
            private String cid;
            private long time;
            private String upload_id;
            private String track_id;
            private List<String> faces;
            private String image;
            private String front_value;
            private String sharpen_ness;
            private String scale_out_y;
            private String scale_out_x;
            private String upload_face_size;
            private List<String> position;
            private String person_type;
            private String person_id;
            private String person_name;
            private String sex;
            private String age;
            private String confidence;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }



    public String getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(String upload_id) {
        this.upload_id = upload_id;
    }

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFront_value() {
        return front_value;
    }

    public void setFront_value(String front_value) {
        this.front_value = front_value;
    }

    public String getSharpen_ness() {
        return sharpen_ness;
    }

    public void setSharpen_ness(String sharpen_ness) {
        this.sharpen_ness = sharpen_ness;
    }

    public String getScale_out_y() {
        return scale_out_y;
    }

    public void setScale_out_y(String scale_out_y) {
        this.scale_out_y = scale_out_y;
    }

    public String getScale_out_x() {
        return scale_out_x;
    }

    public void setScale_out_x(String scale_out_x) {
        this.scale_out_x = scale_out_x;
    }

    public String getUpload_face_size() {
        return upload_face_size;
    }

    public void setUpload_face_size(String upload_face_size) {
        this.upload_face_size = upload_face_size;
    }


    public String getPerson_type() {
        return person_type;
    }

    public void setPerson_type(String person_type) {
        this.person_type = person_type;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<String> getFaces() {
        return faces;
    }

    public void setFaces(List<String> faces) {
        this.faces = faces;
    }

    public List<String> getPosition() {
        return position;
    }

    public void setPosition(List<String> position) {
        this.position = position;
    }
}
