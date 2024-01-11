package com.w83ll43.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorization {

    String value() default "";

    String message() default "";
}
