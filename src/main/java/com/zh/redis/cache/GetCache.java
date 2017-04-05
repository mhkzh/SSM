package com.zh.redis.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
/** 
 * 自定义注解,对于查询使用缓存的方法加入该注解 
 * @author Chenth 
 */  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})  
public @interface GetCache {  
    String name() default "";  
    String value() default "";  
}  