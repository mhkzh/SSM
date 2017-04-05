package com.zh.cxf;

import javax.xml.namespace.QName;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


public class WebServiceUtil {
	
	
    /**区别：静态调用需要依赖service类，因为客户端调用cxf webservice接口的过程中需要服务器端提供service，很不方便，如果同一个项目中则没有区别。
	动态调用完全不依赖service类，服务器端只要提供接口名和路径就可以方便的调用。**/
	
		public static void main(String[] args) {
			/**动态调用**/
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
	        org.apache.cxf.endpoint.Client client = dcf
	                .createClient("http://127.0.0.1:8000/webservice/HelloWorld?wsdl");
	        // url为调用webService的wsdl地址
	        QName name = new QName("http://cxf.zh.com/", "say");
	        // namespace是命名空间，methodName是方法名
	        String xmlStr = "aaaaaaaa";
	        // paramvalue为参数值
	        Object[] objects;
	        try {
	            objects = client.invoke(name, xmlStr);
	            System.out.println(objects[0].toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
			/**静态调用**/
//	        // 创建WebService客户端代理工厂
//	        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//	        // 判断是否抛出异常
//	        factory.getOutInterceptors().add(new LoggingInInterceptor());
//	        // 注册webservice接口
//	        factory.setServiceClass(HelloWorld.class);
//	        // 配置webservice地址
//	        factory.setAddress("http://127.0.0.1:8000/webservice/HelloWorld?wsdl");
//	        // 获得接口对象
//	        HelloWorld service = (HelloWorld) factory.create();
//	        // 调用接口方法
//	        String result = service.say("111");
//	        System.out.println("调用结果:" + result);
//	        // 关闭接口连接
//	        System.exit(0);
	        
		}
	} 
