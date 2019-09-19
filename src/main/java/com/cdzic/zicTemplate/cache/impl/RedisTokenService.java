package com.cdzic.zicTemplate.cache.impl;

import com.cdzic.zicTemplate.cache.abs.IRedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RedisTokenService extends IRedisService<Object> {
    @Value("${redis.token.space}")
    private String redisSpace;

    @Override
    protected String getRedisKey() {
        return redisSpace;
    }
}
