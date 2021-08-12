package com.hyd.handle;

import com.alibaba.fastjson.JSON;
import com.hyd.bean.CommonResult;
import com.hyd.bean.ErrorCode;
import com.hyd.utils.ServletUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录失败处理
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) {
		ServletUtil.renderString(httpServletResponse, JSON.toJSONString(CommonResult.failure(ErrorCode.LOGIN_FAIL)));
	}
}
