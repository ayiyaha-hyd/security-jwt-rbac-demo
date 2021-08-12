package com.hyd.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hyd.bean.CommonResult;
import com.hyd.bean.MyUserDetails;
import com.hyd.utils.JwtTokenUtil;
import com.hyd.utils.ServletUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录成功处理
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
		String jwtToken = JwtTokenUtil.generateToken(userDetails.getUsername(), 300, "_secret");
		JSONObject data = new JSONObject();
		data.put("jwtToken", jwtToken);
		ServletUtil.renderString(httpServletResponse, JSON.toJSONString(CommonResult.success("登录成功！", data)));
	}
}
