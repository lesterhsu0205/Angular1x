package com.lester.core.dao.impl;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lester.core.dao.ICommonDao;
import com.lester.support.util.DlDaoSupport;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CommonDaoImpl extends DlDaoSupport implements ICommonDao {


}
