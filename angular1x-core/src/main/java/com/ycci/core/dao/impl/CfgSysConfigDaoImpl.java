package com.ycci.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ycci.core.dao.ICfgSysConfigDao;
import com.ycci.core.model.CfgSystemConfig;
import com.ycci.core.model.StatusDefault;
import com.ycci.support.util.DlDaoSupport;
import com.ycci.support.util.Session;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CfgSysConfigDaoImpl extends DlDaoSupport implements ICfgSysConfigDao{

	
	@Override
	public List<CfgSystemConfig> queryByCodeCate(String codeCate) throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-CfgSysConfigDaoImpl-queryByCodeCate");
		session.addParams("CODE_CATE", codeCate);
		session.setTransformBean(CfgSystemConfig.class);
		return session.selectList();
	}

	@Override
	public StatusDefault queryDefaultStatus() throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-CfgSysConfigDaoImpl-queryDefaultStatus");
		session.setTransformBean(StatusDefault.class);
		List<StatusDefault> resList = session.selectList();
		return (resList != null && resList.size() > 0) ? resList.get(0) : null;
	}
}
