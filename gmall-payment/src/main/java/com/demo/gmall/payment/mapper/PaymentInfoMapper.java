package com.demo.gmall.payment.mapper;

import com.demo.gmall.bean.PaymentInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/11 20:07
 **/

@org.apache.ibatis.annotations.Mapper
public interface PaymentInfoMapper extends Mapper<PaymentInfo> {
}
