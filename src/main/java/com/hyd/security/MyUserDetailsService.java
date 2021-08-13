package com.hyd.security;

import com.hyd.bean.MyUserDetails;
import com.hyd.bean.SysUser;
import com.hyd.service.SysPermissionService;
import com.hyd.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysPermissionService sysPermissionService;
	@Override
	public UserDetails loadUserByUsername(String username){
		//构建用户信息的逻辑(取数据库/LDAP等用户信息)
		SysUser sysUser = sysUserService.selectUserByUserName(username);
		if(Objects.isNull(sysUser)){
			return null;
		}
		//获取用户权限信息
		Set<? extends GrantedAuthority> authorities = sysPermissionService.getAuthority(sysUser);
		return createLoginUser(sysUser,authorities);
	}

	/**
	 * 将系统用户信息封装为核心安全用户信息
	 */
	private UserDetails createLoginUser(SysUser sysUser,Set<? extends GrantedAuthority> authorities){
		MyUserDetails userInfo = new MyUserDetails();
		userInfo.setUsername(sysUser.getUsername());
		userInfo.setPassword(sysUser.getPassword());
		userInfo.setAuthorities(authorities);
		return userInfo;
	}
}
