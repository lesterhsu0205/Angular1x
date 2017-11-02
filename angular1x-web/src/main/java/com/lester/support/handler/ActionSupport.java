package com.lester.support.handler;

import com.lester.core.config.SysConfig;
import com.lester.core.model.CfgAuthUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class ActionSupport {
	
	public CfgAuthUser getCfgAuthUser() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		attr.getRequest().getRemoteUser();
		HttpSession session = attr.getRequest().getSession();
		CfgAuthUser user = (CfgAuthUser) session.getAttribute(SysConfig.SessionKey.LOGINUSER);
		return user;
	}
	
	
}
