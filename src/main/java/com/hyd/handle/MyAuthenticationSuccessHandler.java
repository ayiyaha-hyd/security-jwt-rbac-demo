package com.hyd.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录成功处理
 */
//@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
		LOGGER.debug("登录成功");
	}
}
