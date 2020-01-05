package com.demo.gmall.user.controller;


import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.gmall.bean.UmsMember;
import com.demo.gmall.bean.UmsMemberReceiveAddress;
import com.demo.gmall.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/23 14:43
 **/
@RestController
public class UserController {

    @Reference
    UserService userService;

    @RequestMapping("getReceiveAddressByMemberId")
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId){

        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = userService.getReceiveAddressByMemberId(memberId);

        return umsMemberReceiveAddresses;
    }


    @RequestMapping("getAllUser")
    public List<UmsMember> getAllUser(){

        List<UmsMember> umsMembers = userService.getAllUser();

        return umsMembers;
    }

    @RequestMapping("index")
    public String index(){
        return "hello user";
    }









}
