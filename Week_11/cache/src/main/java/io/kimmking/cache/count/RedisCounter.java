package io.kimmking.cache.count;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCounter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void incr(String id){
        if(redisTemplate.boundValueOps(id).get()==null){
            redisTemplate.boundValueOps(id).set("1");
        }else{
            redisTemplate.boundValueOps(id).increment(1);
        }
    }

    public String get(String id){
        return redisTemplate.boundValueOps(id).get();
    }
}
