package com.w83ll43.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.w83ll43.common.Result;
import com.w83ll43.domain.entity.History;
import com.w83ll43.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    /**
     * 分页获取历史记录
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Result<List<History>> list(Integer pageNo, Integer pageSize) {
        Page<History> page = new Page<>(pageNo, pageSize);
        historyService.page(page);
        return Result.success(page.getRecords());
    }
}
