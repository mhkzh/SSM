package com.zh.redis.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
/** 
 * 自定义注解,在插入、更新或者删除的时候更新对应的版本 
 * @author zh 
 */  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})  
public @interface PutCache {  
    String name() default "";  
    String value() default "";  
}  
