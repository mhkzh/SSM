package com.zh.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{

	/**
	ʹ��Spring��AbstractRoutingDataSource����������չ������Դ��
	������൱��һ��dataSource��·�ɣ����ڸ���keyֵ�������л���Ӧ��dataSource��
	**/
	@Override
	protected Object determineCurrentLookupKey() {
		 return DataSourceHolder.getDataSources();
	}

}
