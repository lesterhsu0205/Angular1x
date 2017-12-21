package com.lester.support.handler;

import com.lester.config.SysConst;
import com.lester.core.model.CfgAuthUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionSupport {

    public CfgAuthUser getCfgAuthUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getRequest().getRemoteUser();
        HttpSession session = attr.getRequest().getSession();
        CfgAuthUser user = (CfgAuthUser) session.getAttribute(SysConst.SessionKey.LOGINUSER);
        return user;
    }

    public String getLocalURL(HttpServletRequest request) {
        StringBuffer sbf = new StringBuffer("http://");
        sbf.append(request.getServerName())                                     //ip
                .append(":")
                .append(request.getLocalPort())                                 //port
                .append(request.getServletContext().getContextPath())           // HPOSR
                .append(request.getServletPath());                              //rest
        return sbf.toString();
    }
}
