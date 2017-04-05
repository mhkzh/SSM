package com.zh.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{

	/**
	使用Spring的AbstractRoutingDataSource类来进行拓展多数据源。
	该类就相当于一个dataSource的路由，用于根据key值来进行切换对应的dataSource。
	**/
	@Override
	protected Object determineCurrentLookupKey() {
		 return DataSourceHolder.getDataSources();
	}

}
