package com.hyd.service.impl;

import com.hyd.bean.SysUser;
import com.hyd.service.SysPermissionService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
	@Override
	public Set<? extends GrantedAuthority> getAuthority(SysUser sysUser) {
		/* 模拟从数据库中获取到用户权限信息 */
		Set authorities  = new HashSet();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
		authorities.add(authority);
		return authorities;
	}
}
