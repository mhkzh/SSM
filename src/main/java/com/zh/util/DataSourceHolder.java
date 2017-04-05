package com.zh.util;

/**
 * ����Դ
 * @author Administrator
 *
 */
public class DataSourceHolder {
	 //������ʹ����ThreadLocal������������Դ���ṩ�߳��ڲ��ľֲ��������ڱ��߳�����ʱ��ؿ�ȡ�����������̡߳�
	 private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();
	 
	    public static void setDataSources(String dataSource) {
	        dataSources.set(dataSource);
	    }
	    public static String getDataSources() {
	        return dataSources.get();
	    }
}
