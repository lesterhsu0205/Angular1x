package com.ycci.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.ycci.core.config.SysConfig;
import com.ycci.support.util.LogUtil;

@Component
public class DLLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		LogUtil.info(getClass(), "SimpleUrlLogoutSuccessHandler start...");
		request.getSession().removeAttribute(SysConfig.SessionKey.LOGINUSER);
		LogUtil.info(getClass(), "SimpleUrlLogoutSuccessHandler end");
		super.onLogoutSuccess(request, response, authentication);
	}
}