package com.w83ll43.mapper;

import com.w83ll43.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author w83ll43
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-12-19 14:32:00
* @Entity com.w83ll43.domain.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




