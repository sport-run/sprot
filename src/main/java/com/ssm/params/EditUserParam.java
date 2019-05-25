package com.ssm.params;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class EditUserParam {
    private String uid;
    @NotBlank(message = "phone 不能为空")
    private String phone;
    private Integer height;
    @NotNull(message = "age 不能为空")
    private Integer age;
    @NotNull(message = "sex 不能为空")
    private Integer sex;
    @NotNull(message = "weight 不能为空")
    private Integer weight;
    @NotBlank(message = "image 不能为空")
    private String image;
    /**
     * 用户名
     */
    private String username;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
