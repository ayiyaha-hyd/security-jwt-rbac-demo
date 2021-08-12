package com.hyd.handle;

import com.alibaba.fastjson.JSON;
import com.hyd.bean.CommonResult;
import com.hyd.bean.ErrorCode;
import com.hyd.utils.ServletUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限不足处理（返回JSON格式）
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e){
		ServletUtil.renderString(httpServletResponse, JSON.toJSONString(CommonResult.failure(ErrorCode.NO_PERMISSION)));
	}
}
