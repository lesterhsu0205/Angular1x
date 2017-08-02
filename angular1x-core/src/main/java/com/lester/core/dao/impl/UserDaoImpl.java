package com.lester.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lester.core.dao.IUserDao;
import com.lester.core.model.CfgAuthUser;
import com.lester.support.util.CommonUtil;
import com.lester.support.util.DlDaoSupport;
import com.lester.support.util.LogUtil;
import com.lester.support.util.Pagination;
import com.lester.support.util.Session;
import com.lester.support.viewModel.Option;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserDaoImpl extends DlDaoSupport implements IUserDao{
	
	@Override
	public List<CfgAuthUser> queryUsers(Pagination pagination) throws Exception{
		LogUtil.info(getClass(), ",param:" + CommonUtil.toJson(pagination));
		Session session = super.getSession();
		session.setSqlName("queryUsers");
		session.addParams("page", pagination);
		session.setTransformBean(CfgAuthUser.class);
		return session.selectList();
	}

	@Override
	public Integer inserUser(CfgAuthUser user) throws Exception {
		LogUtil.info(getClass(), ",param:" + CommonUtil.toJson(user));
		Session session = super.getSession()
				.setSqlName("insertUser")
				.setObjParam(user);
		int count = session.insertByObj();
		return count;
	}

	@Override
	public Integer deleteUserById(String userId) throws Exception {
		LogUtil.info(getClass(), ",param:" + CommonUtil.toJson(userId));
		Session session = super.getSession()
				.setSqlName("deleteUser")
				.setObjParam(userId);
		return session.deleteByObj();
	}

	@Override
	public Integer updateUser(CfgAuthUser user) throws Exception {
		LogUtil.info(getClass(), ",param:" + CommonUtil.toJson(user));
		Session session = super.getSession()
				.setSqlName("updateUser")
				.setObjParam(user);
		return session.updateByObj();
	}

	@Override
	public String queryUsersCount() throws Exception {
		return super.getSqlSession().selectOne("queryUsersCount");
	}

	@Override
	public List<Option> queryDepOpts() throws Exception {
		Session session = super.getSession();
		session.setSqlName("queryDepOpts");
		List<Map<String, Object>> maps = session.selectMap();
		List<Option> opts = new ArrayList<>();
		for (Map<String, Object> map : maps) {
			opts.add(new Option(map.get("value"), map.get("text")));
		}
		return opts;
	}

	@Override
	public List<Option> queryRoleOpts() throws Exception {
		Session session = super.getSession();
		session.setSqlName("queryRoleOpts");
		List<Map<String, Object>> maps = session.selectMap();
		List<Option> opts = new ArrayList<>();
		for (Map<String, Object> map : maps) {
			opts.add(new Option(map.get("value"), map.get("text")));
		}
		return opts;
	}

	@Override
	public CfgAuthUser loadUserByUsername(String userName, boolean ignoreSts) throws Exception {
		LogUtil.info(getClass(), ",param:userName=" + userName + ",ignoreSts=" + ignoreSts);
		Session session = super.getSession();
		session.setSqlName("loadUserByUsername");
		session.addParams("userName", userName);
		session.addParams("status", ignoreSts ? null : true);
		session.setTransformBean(CfgAuthUser.class);
		List<CfgAuthUser> users = session.selectList();
		return users != null && users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public boolean isHasUser(String userName, String oldPassword) throws Exception {
		LogUtil.info(getClass(), ",param:userName=" + userName + ",oldPassword=" + oldPassword);
		Session session = super.getSession();
		session.setSqlName("isHasUser");
		session.addParams("userName", userName);
		session.addParams("password", DigestUtils.sha512Hex(oldPassword));
		String count = session.selectOne();
		return !"0".equals(count);
	}

	@Override
	public Integer updatePassword(CfgAuthUser user) throws Exception {
		LogUtil.info(getClass(), ",param:" + CommonUtil.toJson(user));
		Session session = super.getSession().setSqlName("updatePassword");
		session.addParams("id", user.getId());
		session.addParams("password", user.getPassword());
		session.addParams("updateUser", user.getUpdateUser());
		return session.update();
	}
}
