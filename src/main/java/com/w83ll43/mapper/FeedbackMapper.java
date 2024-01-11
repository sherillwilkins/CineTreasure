package com.w83ll43.mapper;

import com.w83ll43.domain.entity.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author w83ll43
* @description 针对表【feedback(用户反馈表)】的数据库操作Mapper
* @createDate 2024-01-10 21:19:15
* @Entity com.w83ll43.domain.entity.Feedback
*/
@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {

}




