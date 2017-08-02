package com.ycci.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.ycci.core.config.SysConfig;
import com.ycci.core.model.CfgAuthUser;
import com.ycci.core.service.IUserService;
import com.ycci.support.util.LogUtil;

public class DLLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private IUserService userservice;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		LogUtil.info(this.getClass(), "onAuthenticationSuccess start...");
		if(authentication.isAuthenticated()){
			String username = authentication.getName();
			try {
				CfgAuthUser queryUser = userservice.loadUserByUsername(username);
				LogUtil.info(this.getClass(), "set queryUser to session...userName=" + queryUser.getName());
				request.getSession().setAttribute(SysConfig.SessionKey.LOGINUSER, queryUser);
			} catch (Exception e) {
				LogUtil.error(this.getClass(), "err00", "onAuthenticationSuccess error...", e);
			}
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
}