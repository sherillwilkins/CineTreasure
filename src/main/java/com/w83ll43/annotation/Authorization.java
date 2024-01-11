package com.w83ll43.annotation;

import com.w83ll43.domain.enums.Role;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorization {

    Role value() default Role.VISTOR;

    String message() default "";
}
