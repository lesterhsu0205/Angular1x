package com.lester.core.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lester.core.facade.IUserFacade;
import com.lester.core.model.CfgAuthUser;
import com.lester.core.model.ModifyPasswordParam;
import com.lester.core.model.ResultMsg;
import com.lester.support.handler.ActionSupport;
import com.lester.support.util.CommonUtil;
import com.lester.support.util.LogUtil;
import com.lester.support.util.Pagination;
import com.lester.support.viewModel.Option;
import com.lester.support.viewModel.UserBoardEditReq;
import com.lester.support.viewModel.UserBoardInitResp;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserBoardAction extends ActionSupport {

	@Autowired
	private IUserFacade userFacade;
	
	@RequestMapping(value = "/userBoard/init.action", method = RequestMethod.POST)	
	public @ResponseBody UserBoardInitResp init(@RequestBody Pagination pagination) throws Exception{
		LogUtil.info(getClass(),  ",req:" + CommonUtil.toJson(pagination));
		List<CfgAuthUser> cfgAuthUsers = userFacade.queryUsers(pagination);
		String cfgAuthUserCount = userFacade.queryUsersCount();
		List<Option> depOpts = userFacade.queryDepOpts();
		List<Option> roleOpts = userFacade.queryRoleOpts();
		UserBoardInitResp resp = new UserBoardInitResp(cfgAuthUsers, cfgAuthUserCount, depOpts, roleOpts);
		LogUtil.info(getClass(), ",resp:" + CommonUtil.toJson(resp));
		return resp;
	}
	
	@RequestMapping(value = "/userBoard/edit.action", method = RequestMethod.POST)	
	public @ResponseBody ResultMsg edit(@RequestBody UserBoardEditReq req) throws Exception{
		LogUtil.info(getClass(), ",req:" + CommonUtil.toJson(req));
		ResultMsg res = null;
		
		if(req != null && req.getIsCreate() != null){
			CfgAuthUser loginUser = getCfgAuthUser();
			if(req.getIsCreate()) {
				res = userFacade.inserUser(req, loginUser);
			} else {
				res = userFacade.updateUser(req, loginUser);
			}
		}
		LogUtil.info(getClass(), ",resp:" + CommonUtil.toJson(res));
		
		return res;
	}
	
	@RequestMapping(value = "/userBoard/modifyPassword.action", method = RequestMethod.POST)	
	public @ResponseBody ResultMsg modifyPassword(@RequestBody(required = false) ModifyPasswordParam param) throws Exception{
		LogUtil.info(getClass(), ",req:" + CommonUtil.toJson(param));
		ResultMsg resp = null;
		CfgAuthUser userData = super.getCfgAuthUser();
		resp = userFacade.modifyPassword(userData, param);
		LogUtil.info(getClass(), ",resp:" + CommonUtil.toJson(resp));
		return resp;
	}
	
	@RequestMapping(value = "/userBoard/revertPassword.action", method = RequestMethod.POST)	
	public @ResponseBody ResultMsg revertPassword(@RequestBody CfgAuthUser user) throws Exception{
		LogUtil.info(getClass(), ",req:" + CommonUtil.toJson(user));
		
		CfgAuthUser loginUser = super.getCfgAuthUser();
		ResultMsg res = userFacade.revertPassword(user, loginUser);
		
		LogUtil.info(getClass(), ",resp:" + CommonUtil.toJson(res));
		return res;
	}
}
