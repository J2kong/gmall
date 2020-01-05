package com.mydemo.gmall.user.mapper;

import com.demo.gmall.bean.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/9/23 14:45
 **/

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<UmsMember>{

   List<UmsMember> selectAllUser();
}
