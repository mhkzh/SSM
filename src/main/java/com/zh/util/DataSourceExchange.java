package com.zh.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * ��ȡ��̬����Դ����������
 * @author zh
 *
 */
//@Component����ͨpojoʵ������spring�����У��൱�������ļ��е�<bean id="" class=""/>
//@Component
//@Aspect
public class DataSourceExchange{
	
	    //�������е�service����, Pointcut ��ָ��Щ������Ҫ��ִ��AOP,
//		@Pointcut("execution( * com.zh.service.*.*(..))")
//		public void readMethod() {
//		}// ƥ�����еĶ�ȡ����
//		
   /**
    * @param point
    */
//   @Before("readMethod()")
   public void before(JoinPoint point) {
       //��ȡĿ������������
       Class<?> aClass = point.getTarget().getClass();
       //��ȡ�����������ֲ�ͬ����Դ
       String whichDataSource = aClass.getName().substring(25, aClass.getName().lastIndexOf("."));
       if ("ssmone".equals(whichDataSource)) {
           DataSourceHolder.setDataSources(Constants.DATASOURCE_ONE);
       } else {
           DataSourceHolder.setDataSources(Constants.DATASOURCE_TWO);
       }
   }
   
   /**
    * ִ�к�����Դ��Ϊ��
    */
//    @After("readMethod()")
    public void after() {
        DataSourceHolder.setDataSources(null);
    }


}
