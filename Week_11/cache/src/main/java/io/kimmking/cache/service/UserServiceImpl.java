package io.kimmking.cache.service;

import io.kimmking.cache.count.RedisCounter;
import io.kimmking.cache.entity.User;
import io.kimmking.cache.lock.RedisLock;
import io.kimmking.cache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisLock redisLock;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisCounter redisCounter;

    // 开启spring cache，与mybatis的缓存区分开
    @Cacheable(key="#id",value="userCache")
    public User find(int id) {
        String value = System.currentTimeMillis() + 10000 + "";
        if(!redisLock.lock1("service",value)){
            //实现重试功能
        }
        System.out.println(" ==> find " + id);
        User user=userMapper.find(id);
        redisCounter.incr(id+"");
        System.out.println("访问次数===="+redisCounter.get(id+""));
        redisLock.unlock("service",value);
        return user;
    }

    // 开启spring cache
    @Cacheable //(key="methodName",value="userCache")
    public List<User> list(){
        return userMapper.list();
    }

}
