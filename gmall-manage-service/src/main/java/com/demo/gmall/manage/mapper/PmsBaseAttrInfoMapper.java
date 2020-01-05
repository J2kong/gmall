package com.demo.gmall.manage.mapper;

import com.demo.gmall.bean.PmsBaseAttrInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/25 21:55
 **/
@org.apache.ibatis.annotations.Mapper
public interface PmsBaseAttrInfoMapper extends Mapper<PmsBaseAttrInfo> {
    List<PmsBaseAttrInfo> selectAttrValueListByValueId(@Param("valueIdStr") String valueIdStr);
}
