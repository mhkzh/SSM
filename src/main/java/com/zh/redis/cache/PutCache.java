package com.zh.redis.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
/** 
 * �Զ���ע��,�ڲ��롢���»���ɾ����ʱ����¶�Ӧ�İ汾 
 * @author zh 
 */  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})  
public @interface PutCache {  
    String name() default "";  
    String value() default "";  
}  
