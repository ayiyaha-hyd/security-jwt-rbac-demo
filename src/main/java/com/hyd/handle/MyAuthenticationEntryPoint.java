package com.hyd.handle;

import com.alibaba.fastjson.JSON;
import com.hyd.bean.CommonResult;
import com.hyd.bean.ErrorCode;
import com.hyd.utils.ServletUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证失败处理
 *
 * @author cat
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) {
		ServletUtil.renderString(httpServletResponse, JSON.toJSONString(CommonResult.failure(ErrorCode.AUTH_FAIL, e.getMessage())));
	}
}
