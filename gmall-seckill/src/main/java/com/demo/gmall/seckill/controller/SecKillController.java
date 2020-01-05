package com.demo.gmall.seckill.controller;

import com.demo.gmall.util.ActiveMQUtil;
import com.demo.gmall.util.RedisUtil;
import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/20 15:42
 **/
@Controller
public class SecKillController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private ActiveMQUtil activeMQUtil;
    /***
     * 随机拼运气式秒杀
     * @return
     */
    @RequestMapping("secKill")
    public void kill() {

        Jedis jedis = redisUtil.getJedis();
        jedis.watch("iPhonex");
        int stock = Integer.parseInt(jedis.get("iPhonex"));

        if (stock > 0) {
            Transaction multi = jedis.multi();
            multi.incrBy("iPhonex", -1);
            List<Object> exec = multi.exec();
            if (exec != null && exec.size() > 0) {
                System.out.println("success");

            } else {
                System.out.println("fail");
            }
        }
        jedis.close();
    }
    /***
     * 先到先得式秒杀
     * @return
     */
    @RequestMapping("secKill1")
    public void kill1() {

        Jedis jedis = redisUtil.getJedis();
        RSemaphore semaphore = redissonClient.getSemaphore("iPhonex");
        int stock = Integer.parseInt(jedis.get("iPhonex"));
        if (semaphore.tryAcquire()&&stock>0) {
            System.out.println("success");

        } else {
            System.out.println("fail");
        }
        jedis.close();
    }
}
