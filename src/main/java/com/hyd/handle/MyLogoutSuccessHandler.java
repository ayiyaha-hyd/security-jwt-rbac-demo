package com.hyd.handle;

import com.alibaba.fastjson.JSON;
import com.hyd.bean.CommonResult;
import com.hyd.utils.ServletUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登出成功处理
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
		ServletUtil.renderString(httpServletResponse, JSON.toJSONString(CommonResult.success("登出成功！")));
	}
}
