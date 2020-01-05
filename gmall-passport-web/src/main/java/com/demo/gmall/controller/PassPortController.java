package com.demo.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.demo.gmall.bean.UmsMember;
import com.demo.gmall.config.AuthConfig;
import com.demo.gmall.service.UserService;
import com.demo.gmall.util.HttpclientUtil;
import com.demo.gmall.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import jdk.nashorn.internal.parser.Token;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/3 21:46
 **/
@Controller
public class PassPortController {
    @Reference
    private UserService userService;


    @RequestMapping("vlogin")
    public String vlogin(String code, HttpServletRequest request) {


        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("client_id", AuthConfig.wb_client_id);
        paramMap.put("client_secret", AuthConfig.wb_client_secret);
        paramMap.put("grant_type", AuthConfig.grant_type);
        paramMap.put("redirect_uri", AuthConfig.redirect_uri);
        paramMap.put("code", AuthConfig.wb_code);
        String access_token_post = HttpclientUtil.doPost(AuthConfig.WB_ACCESSTOKEN_URL, paramMap);

        Map<String, Object> access_map = JSON.parseObject(access_token_post, Map.class);

        String uid = (String) access_map.get("uid");
        String access_token = (String) access_map.get("access_token");
        String user_json = HttpclientUtil.doGet(AuthConfig.WB_USERINFO_URL + access_token + "&uid=" + uid);
        Map<String, Object> user_map = JSON.parseObject(user_json, Map.class);

        // 将用户信息保存数据库，用户类型设置为微博用户
        UmsMember umsMember = new UmsMember();

        umsMember.setSourceType(AuthConfig.wb_source_type).setAccessToken(access_token).setAccessCode(code)
                .setSourceUid((String) user_map.get("idstr")).setNickname((String) user_map.get("screen_name"))
                .setCity((String) user_map.get("location"));
        String g = "0";
        String gender = (String) user_map.get("gender");
        if (gender.equals("m")) {
            g = "1";
        }
        umsMember.setGender(g);

        UmsMember umsCheck = new UmsMember();
        umsCheck.setSourceUid(umsMember.getSourceUid());
        UmsMember umsMemberCheck = userService.checkOauthUser(umsCheck);

        if (umsMemberCheck == null) {
            umsMember = userService.addOauthUser(umsMember);
        } else {
            umsMember = umsMemberCheck;
        }
        String nickname = umsMember.getNickname();
        String memberId = umsMember.getId();

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("memberId", memberId);
        userMap.put("nickname", nickname);
        userService.sendUserLoginTopic(memberId, nickname);
        String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
        if (StringUtils.isBlank(ip)) {
            ip = request.getRemoteAddr();// 从request中获取ip
            if (StringUtils.isBlank(ip)) {
                ip = "127.0.0.1";
            }
        }

        String token = JwtUtil.encode("gmall2019", userMap, ip);
        userService.addUserToken(token, memberId);

        return "redirect:http://search.gmall.com:8083/index?token=" + token;
    }


    @GetMapping("index")
    public String index(String ReturnUrl, ModelMap map) {
        map.put("ReturnUrl", ReturnUrl);
        return "index";
    }

    @GetMapping("verify")//两次request ip不能从本次的request拿
    public String verify(String token, String currentIp) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> decode = JwtUtil.decode(token, "2019gmall", currentIp);

        if (decode != null) {
            map.put("status", "success");
            map.put("memberId", decode.get("memberId"));
            map.put("nickname", decode.get("nickname"));
        } else {
            map.put("status", "fail");
        }

        return JSON.toJSONString(map);
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(UmsMember umsMember, HttpServletRequest request) {
        String token = null;
        UmsMember login = userService.login(umsMember);
        if (login != null) {
            String memberId = login.getId();
            String nickname = login.getNickname();
            userService.sendUserLoginTopic(memberId, nickname);
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("memberId", memberId);
            userMap.put("nickname", nickname);

            //通过nginx转发拿到的ip
            String ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isBlank(ip)) {
                ip = request.getRemoteAddr();
                if (StringUtils.isBlank(ip)) {
                    ip = "127.0.0.1";
                }
            }
            token = JwtUtil.encode("2019gmall", userMap, ip);

            //加入redis
            userService.addUserToken(token, memberId);
        } else {
            token = "fail";
        }
        return token;
    }


}
