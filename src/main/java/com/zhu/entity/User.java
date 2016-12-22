package com.zhu.entity;

import com.zhu.entity.baseconfig.Document;
import org.mongodb.morphia.annotations.Entity;

/**
 * Created by zhu on 2016/11/3.
 * noClassnameStored 去掉数据库多余的  _class 字段
 */
@Entity(value = "user", noClassnameStored = true)
public class User extends Document {

    private String nickName;
    private String safeToken;
    private String pwd;
    private String mobile;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSafeToken() {
        return safeToken;
    }

    public void setSafeToken(String safeToken) {
        this.safeToken = safeToken;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}
