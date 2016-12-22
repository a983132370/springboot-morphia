package com.zhu.ctrl;
import com.zhu.biz.UserBiz;
import com.zhu.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhu on 2016/11/3.
 */
@Controller
public class TestCtrl {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserBiz userBiz;

    @ResponseBody
    @RequestMapping(value = {"/testMongo"}, method= RequestMethod.GET)
    public String testMongo(){
        return JsonUtil.toString(userBiz.testUser());
    }

}
