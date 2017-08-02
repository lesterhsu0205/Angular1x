package com.lester.support.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.lester.core.model.CfgAuthUser;
import com.lester.core.model.CfgSystemConfig;

public class Session{
	
	private SqlSession sqlSession;
	private String sqlName;
	private Class<?> transformBean;
	private Map<String, Object> params;
	private Object objParam;
	
	public Session(SqlSession sqlSession){
		this.sqlSession = sqlSession;
	}
	
	public Session addParams(String key, Object value){
		if(params == null){
			params = new HashMap<String, Object>();
		}
		params.put(key, value);
		return this;
	}
	
	public Session setSqlName(String sqlName) {
		this.sqlName = sqlName;
		return this;
	}

	public Session setTransformBean(Class<?> transformBean) {
		this.transformBean = transformBean;
		return this;
	}

	public Session setObjParam(Object objParam) {
		this.objParam = objParam;
		return this;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}
	
	//搜尋
	public <T> List<T> selectList() throws Exception{
		List<Map<String, Object>> dataList =  sqlSession.selectList(sqlName, params);
		dataList = CommonUtil.colunNameToBeanName(dataList);
		return (List<T>) CommonUtil.mapToBean(transformBean, dataList);
	}
	
	public List<Map<String, Object>> selectMap() throws Exception{
		return sqlSession.selectList(sqlName, params);
	}
	
	public <T> T selectOne() throws Exception{
		return sqlSession.selectOne(sqlName, params);
	}
	

	//插入
	public Integer insertByObj() {
		return sqlSession.insert(sqlName, objParam);
	}
	
	public <T> T insertByRtnObjParam() {
		sqlSession.insert(sqlName, objParam);
		return (T) objParam;
	}
	
	//更新
	public Integer updateByObj() {
		return sqlSession.update(sqlName, objParam);
	}
	
	public Integer update() {
		return sqlSession.update(sqlName, params);
	}
	
	//刪除
	public Integer deleteByObj() {
		return sqlSession.delete(sqlName, objParam);
	}
	
	public Integer delete() {
		return sqlSession.delete(sqlName, params);
	}
	
}
