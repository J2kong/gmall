package com.demo.gmall.manage;

import com.demo.gmall.GmallManageServiceApplication;
import com.demo.gmall.bean.UmsMember;
import com.demo.gmall.manage.mapper.UmsMemberMapper;
import com.demo.gmall.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmallManageServiceApplication.class)
public class GmallManagerServiceApplicationTests {

 /*   @Autowired
    RedisUtil redisUtil;*/
    @Autowired
    private UmsMemberMapper umsMemberMapper;


    @Test
    public void contextLoads() {
  /*    *//*  Jedis jedis = redisUtil.getJedis();
        System.out.println(jedis.ping());*//*
        UmsMember umsMember = new UmsMember();

        umsMemberMapper.insertSelective(umsMember);
        System.out.println(umsMember.getId());*/
    }

}
