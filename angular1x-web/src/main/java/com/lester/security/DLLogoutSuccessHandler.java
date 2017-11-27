package com.lester.security;

import com.lester.config.SysConst;
import com.lester.support.util.LogUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DLLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		LogUtil.info(getClass(), "SimpleUrlLogoutSuccessHandler start...");
        request.getSession().removeAttribute(SysConst.SessionKey.LOGINUSER);
        LogUtil.info(getClass(), "SimpleUrlLogoutSuccessHandler end");
		super.onLogoutSuccess(request, response, authentication);
	}
}