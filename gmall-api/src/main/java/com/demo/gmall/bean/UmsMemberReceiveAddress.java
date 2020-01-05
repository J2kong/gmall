package com.demo.gmall.bean;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class UmsMemberReceiveAddress implements Serializable {

    private static final long serialVersionUID = -7507788292101778218L;

    @Id
    private String id;
    private String memberId;
    private String  name;
    private String  phoneNumber;
    private int defaultStatus;
    private String postCode;
    private String province;
    private String city;
    private String region;
    private String detailAddress;


}
