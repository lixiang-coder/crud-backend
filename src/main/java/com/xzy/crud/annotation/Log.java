package com.xzy.crud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义Log注解
 */
@Target(ElementType.METHOD)		// 标识方法
@Retention(RetentionPolicy.RUNTIME)	// 运行时有效
public @interface Log {
}