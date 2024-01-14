package com.w83ll43.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.domain.entity.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.w83ll43.domain.vo.FeedbackVo;

/**
 * @author w83ll43
 * @description 针对表【feedback(用户反馈表)】的数据库操作Service
 * @createDate 2024-01-10 21:19:15
 */
public interface FeedbackService extends IService<Feedback> {
    Page<FeedbackVo> getFeedbackList(Integer pageNo, Integer pageSize);
}
