package com.zh.util;

/**
 * 数据源
 * @author Administrator
 *
 */
public class DataSourceHolder {
	 //这里我使用了ThreadLocal来保存了数据源，提供线程内部的局部变量，在本线程内随时随地可取，隔离其他线程。
	 private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();
	 
	    public static void setDataSources(String dataSource) {
	        dataSources.set(dataSource);
	    }
	    public static String getDataSources() {
	        return dataSources.get();
	    }
}
