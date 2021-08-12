package com.hyd.service;

import com.hyd.bean.SysUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public interface SysPermissionService {
	public Set<? extends GrantedAuthority> getAuthority(SysUser sysUser);
}
