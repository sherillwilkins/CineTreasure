package com.w83ll43.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.common.Result;
import com.w83ll43.domain.entity.Feedback;
import com.w83ll43.service.FeedbackService;
import com.w83ll43.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * 用户反馈
     * @param feedback
     * @return
     */
    @PostMapping
    public Result<String> feedback(@RequestBody Feedback feedback) {
        Long uid = BaseContext.getCurrentId();
        feedback.setUid(uid);
        feedback.setFtime(new Date());
        feedbackService.save(feedback);
        return Result.success("反馈成功！");
    }

    /**
     * 分页获取反馈列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Result<Page<Feedback>> list(int pageNo, int pageSize) {
        Page<Feedback> page = new Page<>(pageNo, pageSize);
        feedbackService.page(page);
        return Result.success(page);
    }

}
