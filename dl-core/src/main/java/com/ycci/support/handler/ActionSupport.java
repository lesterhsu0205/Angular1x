package com.ycci.support.handler;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ycci.core.config.SysConfig;
import com.ycci.core.model.CfgAuthUser;

public class ActionSupport {
	
	public CfgAuthUser getCfgAuthUser() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		attr.getRequest().getRemoteUser();
		HttpSession session = attr.getRequest().getSession();
		CfgAuthUser user = (CfgAuthUser) session.getAttribute(SysConfig.SessionKey.LOGINUSER);
		return user;
	}
	
	
}
