package com.w83ll43.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.w83ll43.domain.entity.History;
import com.w83ll43.service.HistoryService;
import com.w83ll43.mapper.HistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author w83ll43
* @description 针对表【history(观看历史表)】的数据库操作Service实现
* @createDate 2024-01-10 21:19:15
*/
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History>
    implements HistoryService{

}




