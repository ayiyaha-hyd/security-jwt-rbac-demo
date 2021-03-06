package com.hyd.filter;

import com.hyd.security.MyUserDetailService;
import com.hyd.utils.JwtTokenUtil;
import com.hyd.utils.SecurityUtil;
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
					//用户名对应 principal 属性字段
					//密码对应 credentials 属性字段
					//TODO 但此处用户名和密码都存放在 principal 中 ？？
					UsernamePasswordAuthenticationToken authentication =
							new UsernamePasswordAuthenticationToken(
//									userDetails.getUsername(),
//									userDetails.getPassword(),
									userDetails,
									null,
									userDetails.getAuthorities()
							);

					//设置authentication中details
					//调用父类方法为 details 字段赋值
					//从 request 中 获取 remoteAddress,sessionId 为 WebAuthenticationDetails 赋值
					//details 存储有关身份验证请求的其他详细信息。 这些可能是 IP 地址、证书序列号等
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

					//将当前用户的认证信息放到 Security 上下文信息中
					SecurityUtil.setAuthentication(authentication);
				}
			}
		}
		//继续执行调用链
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
