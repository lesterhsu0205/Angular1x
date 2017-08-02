package com.ycci.support.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class DlDaoSupport extends SqlSessionDaoSupport{

	@Autowired
	public void setSqlSessionBase(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public Session getSession() throws Exception{
		return new Session(super.getSqlSession());
	}
}
