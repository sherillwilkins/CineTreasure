package com.w83ll43.aop;

import com.w83ll43.annotation.Authorization;
import com.w83ll43.common.Result;
import com.w83ll43.domain.enums.Role;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAOP {
    @Around("@annotation(com.w83ll43.annotation.Authorization) && @annotation(authorization)")
    public Object before(ProceedingJoinPoint joinPoint, Authorization authorization) throws Throwable {
        String value = authorization.value();
        int type = Integer.parseInt(authorization.value());
        if (Role.of(type) == Role.USER) {
            return Result.error("普通用户");
        } else if (Role.of(type) == Role.VIPER) {
            return Result.error("VIP用户");
        } else if (Role.of(type) == Role.VISTOR) {
            return Result.error("游客");
        }
        String message = authorization.message();
        System.out.println("value = " + value);
        System.out.println("message = " + message);
        return joinPoint.proceed();
    }
}
