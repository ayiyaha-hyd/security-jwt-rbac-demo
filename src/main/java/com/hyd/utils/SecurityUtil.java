package com.hyd.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Security 工具类
 */
public class SecurityUtil {
	//TODO 可以通过Spring来管理，实现单例
	private static final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();

	/**
	 * 获取 Authentication 对象
	 */
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 更新 Authentication 对象
	 * @param authentication
	 */
	public static void setAuthentication(Authentication authentication){
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	/**
	 * 生成 BCryptPasswordEncoder 加密过的密码
	 */
	public static String encryptPassword(String rawPassword){
		return B_CRYPT_PASSWORD_ENCODER.encode(rawPassword);
	}
}
