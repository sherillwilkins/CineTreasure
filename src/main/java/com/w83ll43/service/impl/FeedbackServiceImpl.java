package com.w83ll43.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.domain.entity.Feedback;
import com.w83ll43.domain.entity.User;
import com.w83ll43.domain.vo.FeedbackVo;
import com.w83ll43.service.FeedbackService;
import com.w83ll43.mapper.FeedbackMapper;
import com.w83ll43.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author w83ll43
 * @description 针对表【feedback(用户反馈表)】的数据库操作Service实现
 * @createDate 2024-01-10 21:19:15
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback>
        implements FeedbackService {

    @Autowired
    private UserService userService;

    @Override
    public Page<FeedbackVo> getFeedbackList(Integer pageNo, Integer pageSize) {
        Page<Feedback> page = new Page<>(pageNo, pageSize);
        this.page(page);
        List<FeedbackVo> feedbackVos = new ArrayList<>();
        if (page.getRecords() != null && page.getRecords().size() > 0) {
            feedbackVos = page.getRecords().stream().map(feedback -> {
                FeedbackVo feedbackVo = new FeedbackVo();
                feedbackVo.setUid(feedback.getUid());
                feedbackVo.setContent(feedback.getContent());
                feedbackVo.setFtime(feedback.getFtime());
                User user = userService.getUserByUid(feedback.getUid());
                feedbackVo.setAvatar(user.getAvatar());
                feedbackVo.setNickname(user.getNickname());
                return feedbackVo;
            }).collect(Collectors.toList());
        }
        Page<FeedbackVo> feedbackVoPage = new Page<>();
        BeanUtil.copyProperties(page, feedbackVoPage);
        feedbackVoPage.setRecords(feedbackVos);
        return feedbackVoPage;
    }
}




