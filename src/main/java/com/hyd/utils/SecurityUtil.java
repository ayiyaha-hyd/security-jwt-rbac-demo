package com.hyd.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtil {
	private static final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();

	/**
	 * 获取 Authentication 对象
	 */
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 生成 BCryptPasswordEncoder 加密过的密码
	 */
	public static String encryptPassword(String rawPassword){
		return B_CRYPT_PASSWORD_ENCODER.encode(rawPassword);
	}
}
