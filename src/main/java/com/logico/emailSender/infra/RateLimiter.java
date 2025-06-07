package com.logico.emailSender.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
    public class RateLimiter {

        private static final int MAX_REQUESTS = 2;
        private static final Duration PERIOD = Duration.ofHours(1);

        @Autowired
        private StringRedisTemplate redisTemplate;

        public boolean isAllowed(String ip) {
            String key = "rate_limit:" + ip;
            Long currentCount = redisTemplate.opsForValue().increment(key);
            currentCount = (currentCount != null) ? currentCount : 1L;
            if (currentCount == 1L) {
                redisTemplate.expire(key, PERIOD);
            }
            return currentCount <= MAX_REQUESTS;
        }
    }