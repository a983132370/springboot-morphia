package com.zhu.entity.query;


import com.zhu.entity.query.base.Condition;

/**
 * Created by zhu on 2016/7/24.
 */
public class UserCondition extends Condition {
    private String nickName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
