package com.lester.security;

import com.lester.config.SysConst;
import com.lester.core.model.CfgAuthUser;
import com.lester.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserService userservice;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			CfgAuthUser queryUser = userservice.loadUserByUsername(username);
			if(queryUser != null) {
	            Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(SysConst.Security.ROLE_USER);
                auths.add(authority);
	            User user = new User(queryUser.getUserName(), queryUser.getPassword(), auths);
	            return user;
	        }
			throw new UsernameNotFoundException("用户不存在");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
