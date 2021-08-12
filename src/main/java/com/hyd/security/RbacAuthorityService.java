package com.hyd.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
		//		authentication.getAuthorities();????
		Object userInfo = authentication.getPrincipal();
		if (userInfo instanceof UserDetails) {
			String username = ((UserDetails) userInfo).getUsername();
			Set<String> urls = new HashSet();
			urls.add("/demo1/**");
			urls.add("/demo/index");
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
		return hasPermission;
	}
}
