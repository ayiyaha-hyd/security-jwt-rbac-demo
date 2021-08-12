package com.hyd.filter;

import com.hyd.security.MyUserDetailService;
import com.hyd.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 认证
 * 过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	Class clazz;
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
	@Autowired
	private MyUserDetailService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

		/* authorization = Bearer eyJhbGciOiJSUzI1... */
		//从请求头获取 authorization 字段
		String authHeader = httpServletRequest.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			//从字段截取出请求携带的 jwtToken
			String authToken = authHeader.substring("Bearer ".length());
			//解析 jwtToken 中存储的数据
			String username = JwtTokenUtil.parseToken(authToken, "_secret");
			//如果请求携带了 jwtToken 数据，但 Security 上下文中并未存储该数据（第一次访问前）
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				//根据用户名username加载userDetails（获取用于安全认证的用户信息）
				UserDetails userDetails = userDetailService.loadUserByUsername(username);
				//如果系统中有该用户信息
				if (userDetails != null) {
					//根据userDetails构建 Authentication
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

					//设置authentication中details
					//TODO 已经根据 userDetails 构建了authentication，为什么此处需要如下操作?
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

					//将当前用户的认证信息放到 Security 上下文信息中
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		//继续执行调用链
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
