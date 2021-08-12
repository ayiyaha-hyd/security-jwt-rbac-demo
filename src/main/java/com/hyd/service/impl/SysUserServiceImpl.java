package com.hyd.service.impl;

import com.hyd.bean.SysUser;
import com.hyd.service.SysUserService;
import com.hyd.utils.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
	@Override
	public SysUser selectUserByUserName(String username) {
		/* 模拟从数据库中查取出用户 */
		if (username != null && "admin".equals(username)) {
			SysUser sysUser = new SysUser();
			//			userInfo.setUsername(username);
			//			userInfo.setPassword(new BCryptPasswordEncoder().encode("123456"));
			sysUser.setUsername("admin");
			sysUser.setPassword(SecurityUtil.encryptPassword("123456"));
			return sysUser;
		}
		return null;
	}
}
