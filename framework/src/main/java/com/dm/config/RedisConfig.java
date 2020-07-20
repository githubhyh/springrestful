package com.dm.config;

import com.alibaba.fastjson.JSONObject;
import com.dm.config.properties.RedisSentinelProperties;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author hu.yuhao
 * */
@Configuration
@EnableCaching
public class RedisConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    //@Bean
    //@Primary
    public LettuceConnectionFactory getConnectionFactory() {
        logger.info("init redis cache, index is 1");
        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
        sentinelConfiguration.setMaster("mymaster");
        sentinelConfiguration.setDatabase(0);
        //sentinelConfiguration.sentinel("127.0.0.1", 6379);
        sentinelConfiguration.setPassword("123456");
        List<RedisNode> nodes = new ArrayList<>();
        nodes.add(new RedisNode("127.0.0.1", 6382));
        nodes.add(new RedisNode("127.0.0.1", 6383));
        nodes.add(new RedisNode("127.0.0.1", 6384));
        sentinelConfiguration.setSentinels(nodes);
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(sentinelConfiguration);
        logger.info(connectionFactory.getPassword());
        return connectionFactory;
    }

    @Bean("sentinelLettuce")
    @Primary
    public LettuceConnectionFactory lettuceConnectionFactory(@Autowired RedisSentinelProperties redisSentinelProperties) {
        logger.info("Initialize sentinel lettuce connection factory");
        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
        sentinelConfiguration.setPassword(redisSentinelProperties.getPassword());
        sentinelConfiguration.setDatabase(redisSentinelProperties.getDatabase());
        sentinelConfiguration.setMaster(redisSentinelProperties.getSentinel().getMaster());
        Set<String> nodes = redisSentinelProperties.getSentinel().getNodes();
        List<RedisNode> redisNodes = new ArrayList<>();
        for (String n:nodes) {
            String[] temps = n.split(":");
            RedisNode redisNode = new RedisNode(temps[0], Integer.parseInt(temps[1]));
            redisNodes.add(redisNode);
        }
        sentinelConfiguration.setSentinels(redisNodes);

        GenericObjectPoolConfig<Object> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(redisSentinelProperties.getPool().getMaxIdle());
        poolConfig.setMinIdle(redisSentinelProperties.getPool().getMinIdle());
        poolConfig.setMaxWaitMillis(redisSentinelProperties.getPool().getMaxWait());
        poolConfig.setMaxTotal(redisSentinelProperties.getPool().getMaxActive());
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(sentinelConfiguration, LettucePoolingClientConfiguration.builder().poolConfig(poolConfig).build());
        return connectionFactory;
    }

    @Bean("redisCacheManager")
    public CacheManager cacheManager(@Qualifier("sentinelLettuce") LettuceConnectionFactory connectionFactory) {
        //return RedisCacheManager.create(getConnectionFactory());
        //logger.info(JSONObject.toJSONString(connectionFactory));
        System.out.println(JSONObject.toJSONString(connectionFactory.getSentinelConfiguration().getSentinels()));
        return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory), RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)));
    }

    @Bean("redisTemplate")
    public RedisTemplate redisTemplate(@Qualifier("sentinelLettuce") LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
