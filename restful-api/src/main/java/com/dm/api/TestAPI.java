package com.dm.api;

import com.alibaba.fastjson.JSONObject;
import com.dm.annotations.RateLimit;
import com.dm.annotations.RedisCache;
import com.dm.config.properties.JavaMailProperties;
import com.dm.domain.Test;
import com.dm.service.TestDao;
import com.dm.utils.email.EmailUtils;
import com.dm.utils.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/test")
public class TestAPI {
    private static final Logger logger = LoggerFactory.getLogger(TestAPI.class);

    @Autowired
    private TestDao testDao;

    @Autowired
    private JavaMailProperties properties;

    @GetMapping("/index")
    @ResponseBody
    public String test(HttpServletRequest request)  {
        logger.info(request.getSession().getId());
        logger.info((String)request.getSession().getAttribute("userID"));
        return "success";
    }

    @GetMapping("/auth")
    @ResponseBody
    public String auth(HttpServletRequest request) {
        request.getSession().setAttribute("userID", "123456");
        return "auth";
    }

    @GetMapping("/unauth")
    @ResponseBody
    public String unauth() {
        return "unauth";
    }

    @RateLimit(sec = 10, limit = 3)
    @GetMapping("/redis/{num}")
    @ResponseBody
    public String redis(@PathVariable Integer num) {
        RedisUtils.set("num", num);
        return (Integer)RedisUtils.getObj("num") + "";
    }

    @RateLimit
    @GetMapping("/insert")
    @ResponseBody
    public String insert() {
        Test test = new Test();
        test.setRemark("test remark");
        testDao.insert(test);
        return "insert";
    }

    @GetMapping("/cache")
    @ResponseBody
    @RedisCache(key = "test-redis-cache", isFromCache = true)
    public List<Test> redisCache() {
        List<Test> tests = testDao.find(new Test());
        logger.info("test redis cache");
        return tests;
    }

    @GetMapping("/redirect")
    public String redirect() {
        return "redirect:cache";
    }

    @PostMapping("/sendMail")
    @ResponseBody
    public String sendMail() {
        logger.info(JSONObject.toJSONString(properties));
        boolean email = EmailUtils.sendEmail("huyuhao", "hu.yuhao@byd.com", "", "test", "this is a test email");
        return email==true?"发送成功":"发送失败";
    }

    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestBody String name) {
        logger.info(name);
        return "test post request";
    }

    @PostMapping("/order")
    @ResponseBody
    public String order(@RequestParam Integer tickets) {
        logger.info("购买票数：{}", tickets+"");
        testDao.orderTickets(tickets);
        return "正常响应";
    }
}
