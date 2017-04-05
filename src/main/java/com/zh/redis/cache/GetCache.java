package com.zh.redis.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
/** 
 * �Զ���ע��,���ڲ�ѯʹ�û���ķ��������ע�� 
 * @author Chenth 
 */  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})  
public @interface GetCache {  
    String name() default "";  
    String value() default "";  
}  