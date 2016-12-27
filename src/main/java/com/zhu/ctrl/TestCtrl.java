package com.zhu.ctrl;
import com.zhu.biz.UserBiz;
import com.zhu.context.RequestContext;
import com.zhu.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhu on 2016/11/24.
 * 测试的controller
 */
@Controller
public class TestCtrl {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    //此注解由spring提供,与@Resource不同 但作用一样.
    @Autowired
    UserBiz userBiz;

    @RequestMapping(value = {"/"}, method= RequestMethod.GET)
    public String testLove(){
        log.debug(RequestContext.getClientIp());

        return "cavanlove/love";
    }
    //返回json
    @ResponseBody
    @RequestMapping(value = {"/testMongo"}, method= RequestMethod.GET)
    public String testMongo(){
        log.debug(RequestContext.getClientIp());
        return JsonUtil.toString(userBiz.testUser());
    }

}
