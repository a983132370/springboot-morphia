package com.zhu.biz;

import com.zhu.dao.AppUserDao;
import com.zhu.entity.User;
import com.zhu.util.DateUtil;
import com.zhu.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhu on 2016/11/27.
 */
@Service
public class UserBiz {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppUserDao dao;

    public  User  testUser(){
        User user = dao.queryByEmail("983132370@qq.com");
        if (user == null) {
            user = new User();
            user.setNickName("TestNickName");
            user.setSafeToken(TokenUtil.createToken());
            user.setEmail("983132370@qq.com");
            user.setCreatedTime(DateUtil.nowInMilliseconds());
            String key = dao.add(user);
            log.debug("this is the UserID :"+key);
        }
       return dao.queryByEmail("983132370@qq.com");
    }
}
