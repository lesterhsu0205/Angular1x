package com.ycci.core.dao;

import java.util.List;

import com.ycci.core.model.CfgSystemConfig;
import com.ycci.core.model.StatusDefault;

public interface ICfgSysConfigDao {

	public List<CfgSystemConfig> queryByCodeCate(String codeCate) throws Exception;
	
	public StatusDefault queryDefaultStatus() throws Exception;
	
}
