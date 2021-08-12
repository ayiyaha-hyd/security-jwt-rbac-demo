package com.hyd.service;

import com.hyd.bean.SysUser;

public interface SysUserService {
	SysUser selectUserByUserName(String username);
}
