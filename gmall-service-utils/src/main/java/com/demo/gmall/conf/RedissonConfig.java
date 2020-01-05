package com.demo.gmall.conf;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/10/9 19:50
 **/
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host:0}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private String port;

    @Bean
    public RedissonClient redissonClient() {
        Config config =new Config();
        config.useSingleServer().setAddress("redis://"+host+":"+port);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }


}
