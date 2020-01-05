package com.demo.gmall.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class UmsMember  implements Serializable {

    private static final long serialVersionUID = -5895731342041581776L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY , generator = "SELECT LAST_INSERT_ID()")
    private String id;
    private String memberLevelId;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private int status;
    private Date createTime;
    private String icon;
    private String gender;
    private Date birthday;
    private String city;
    private String job;
    private String personalizedSignature;
    private String sourceType;
    private int integration;
    private int growth;
    private int luckeyCount;
    private int historyIntegration;
    private String sourceUid;
    private String accessToken;
    private String accessCode;
}