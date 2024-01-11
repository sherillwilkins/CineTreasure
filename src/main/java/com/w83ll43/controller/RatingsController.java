package com.w83ll43.controller;

import com.w83ll43.common.Result;
import com.w83ll43.domain.entity.Ratings;
import com.w83ll43.service.RatingsService;
import com.w83ll43.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/ratings")
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;

    /**
     * 评分
     * @param mid
     * @param score
     * @return
     */
    @PostMapping
    public Result<String> rating(@RequestBody Long mid, @RequestBody Long score) {
        Long uid = BaseContext.getCurrentId();
        Ratings ratings = new Ratings();
        ratings.setUid(uid);
        ratings.setMid(mid);
        ratings.setValue(score);
        ratings.setRtime(new Date());
        ratingsService.save(ratings);

        // TODO 评分人数
        return Result.success("评分成功！");
    }
}
