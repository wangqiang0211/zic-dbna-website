package com.cdzic.zicTemplate.cache.impl;

import com.cdzic.zicTemplate.cache.abs.IRedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @creator yaotao
 * @date 2018/8/21 11:45
 * @describe:
 */
@Service
public class RedisStringService extends IRedisService<String> {

    @Value("${redis.string.space}")
    private String redisKey;

    @Override
    protected String getRedisKey() {
        return redisKey;
    }
}
