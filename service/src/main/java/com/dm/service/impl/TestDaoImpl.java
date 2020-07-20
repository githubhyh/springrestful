package com.dm.service.impl;

import com.dm.domain.Test;
import com.dm.mapper.TestMapper;
import com.dm.service.TestDao;
import com.dm.utils.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestDaoImpl implements TestDao {
    private static final Logger logger = LoggerFactory.getLogger(TestDaoImpl.class);

    @Autowired
    private TestMapper testMapper;

    @Transactional
    @Override
    public int insert(Test test) {
        testMapper.insert(test);
        //int i = 10/0;
        return 1;
    }

    /**
     * 查找test集合
     *
     * @param test 查询参数
     */
    @Cacheable(cacheNames = {"tests"}, key = "'test_dao_list'", cacheManager = "redisCacheManager")
    @Override
    public List<Test> find(Test test) {
        logger.info("do find by test");
        return testMapper.find(test);
    }

    /**
     * 模拟抢票
     *
     * @param tickets 购买票数
     */
    @Override
    public void orderTickets(int tickets) {
        String key = "tickets";

        RedisUtils.watch(key);
        int ticket = (Integer) RedisUtils.getObj(key);
        if (ticket < tickets) {
            logger.info("余票不足");
        }else {
            RedisUtils.multi();
            RedisUtils.set(key, ticket-tickets);
            List<Object> exec = RedisUtils.exec();
            if (exec == null || exec.isEmpty()) {
                logger.info("抢票失败");
            }else {
                logger.info("抢票成功，还剩余票数：{}", RedisUtils.getObj(key)+"");
            }
        }
    }

    @Override
    public void test() {
        TestDao.super.test();
    }
}
