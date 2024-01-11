package com.w83ll43.aop;

import com.w83ll43.annotation.Authorization;
import com.w83ll43.common.Result;
import com.w83ll43.domain.entity.User;
import com.w83ll43.domain.enums.Role;
import com.w83ll43.utils.BaseContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAOP {

    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(com.w83ll43.annotation.Authorization) && @annotation(authorization)")
    public Object before(ProceedingJoinPoint joinPoint, Authorization authorization) throws Throwable {
        Long uid = BaseContext.getCurrentId();
        User user = (User) redisTemplate.opsForValue().get("uid:" + uid);
        Role role = authorization.value();
        int type = user.getRole();
        if (Role.of(type) != role) {
            return Result.error(authorization.message());
        }
        return joinPoint.proceed();
    }
}
