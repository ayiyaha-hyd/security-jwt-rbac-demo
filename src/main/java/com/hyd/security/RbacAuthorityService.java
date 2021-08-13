package com.hyd.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限配置
 */
@Component("rbacAuthorityService")
public class RbacAuthorityService {
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		boolean hasPermission = false;
		//TODO		authentication.getAuthorities();????
		//此处如果没有获取到对应路径权限，会走权限不足handler
		String username = (String) authentication.getPrincipal();
		if(username==null||"anonymousUser".equals(username)){
			return hasPermission;
		}
		//从缓存/数据库中，获取访问路径权限
		//get urls from database/redis

		Set<String> urls = new HashSet();
		urls.add("/demo/index");
		urls.add("/demo1/**");

		Set set2 = new HashSet();
		Set set3 = new HashSet();

		AntPathMatcher antPathMatcher = new AntPathMatcher();
		for (String url : urls) {
			if (antPathMatcher.match(url, request.getRequestURI())) {
				hasPermission = true;
				break;
			}
		}
		return hasPermission;
	}
}
