package com.zh.cxf;

import javax.xml.namespace.QName;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


public class WebServiceUtil {
	
	
    /**���𣺾�̬������Ҫ����service�࣬��Ϊ�ͻ��˵���cxf webservice�ӿڵĹ�������Ҫ���������ṩservice���ܲ����㣬���ͬһ����Ŀ����û������
	��̬������ȫ������service�࣬��������ֻҪ�ṩ�ӿ�����·���Ϳ��Է���ĵ��á�**/
	
		public static void main(String[] args) {
			/**��̬����**/
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
	        org.apache.cxf.endpoint.Client client = dcf
	                .createClient("http://127.0.0.1:8000/webservice/HelloWorld?wsdl");
	        // urlΪ����webService��wsdl��ַ
	        QName name = new QName("http://cxf.zh.com/", "say");
	        // namespace�������ռ䣬methodName�Ƿ�����
	        String xmlStr = "aaaaaaaa";
	        // paramvalueΪ����ֵ
	        Object[] objects;
	        try {
	            objects = client.invoke(name, xmlStr);
	            System.out.println(objects[0].toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
			/**��̬����**/
//	        // ����WebService�ͻ��˴�����
//	        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//	        // �ж��Ƿ��׳��쳣
//	        factory.getOutInterceptors().add(new LoggingInInterceptor());
//	        // ע��webservice�ӿ�
//	        factory.setServiceClass(HelloWorld.class);
//	        // ����webservice��ַ
//	        factory.setAddress("http://127.0.0.1:8000/webservice/HelloWorld?wsdl");
//	        // ��ýӿڶ���
//	        HelloWorld service = (HelloWorld) factory.create();
//	        // ���ýӿڷ���
//	        String result = service.say("111");
//	        System.out.println("���ý��:" + result);
//	        // �رսӿ�����
//	        System.exit(0);
	        
		}
	} 
