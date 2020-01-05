package com.demo.gmall.manage.service.Impl;

import com.demo.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/10/9 20:39
 **/
@RestController
public class testRedissonController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedissonClient redissonClient;

    @RequestMapping("testRedisson")
    public String redisson() {
        Jedis jedis = redisUtil.getJedis();
        RLock rLock = redissonClient.getLock("lock");
        rLock.lock();

        try {
            String v = jedis.get("k");
            if (StringUtils.isBlank(v)) {
                v = "1";
            }
            System.out.println(v);
            System.out.println("->" + v);
            jedis.set("k", (Integer.parseInt(v) + 1) + "");
            jedis.close();
        } finally {

            rLock.unlock();
        }

        return "success";
    }

}
