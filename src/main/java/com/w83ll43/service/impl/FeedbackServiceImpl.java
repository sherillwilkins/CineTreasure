package com.w83ll43.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.domain.entity.Feedback;
import com.w83ll43.service.FeedbackService;
import com.w83ll43.mapper.FeedbackMapper;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【feedback(用户反馈表)】的数据库操作Service实现
* @createDate 2024-01-10 21:19:15
*/
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback>
    implements FeedbackService{

}




