package com.zh.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 获取动态数据源拦截器方法
 * @author zh
 *
 */
//@Component把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
//@Component
//@Aspect
public class DataSourceExchange{
	
	    //拦截所有的service操作, Pointcut 是指那些方法需要被执行AOP,
//		@Pointcut("execution( * com.zh.service.*.*(..))")
//		public void readMethod() {
//		}// 匹配所有的读取操作
//		
   /**
    * @param point
    */
//   @Before("readMethod()")
   public void before(JoinPoint point) {
       //获取目标对象的类类型
       Class<?> aClass = point.getTarget().getClass();
       //获取包名用于区分不同数据源
       String whichDataSource = aClass.getName().substring(25, aClass.getName().lastIndexOf("."));
       if ("ssmone".equals(whichDataSource)) {
           DataSourceHolder.setDataSources(Constants.DATASOURCE_ONE);
       } else {
           DataSourceHolder.setDataSources(Constants.DATASOURCE_TWO);
       }
   }
   
   /**
    * 执行后将数据源置为空
    */
//    @After("readMethod()")
    public void after() {
        DataSourceHolder.setDataSources(null);
    }


}
