package com.hyd.config;

import com.hyd.filter.JwtAuthenticationTokenFilter;
import com.hyd.handle.*;
import com.hyd.security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyAccessDeniedHandler accessDeniedHandler;
	@Autowired
	private MyAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private MyAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private MyAuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private MyLogoutSuccessHandler logoutSuccessHandler;
	@Autowired
	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	@Autowired
	private MyUserDetailService userDetailService;

	/**
	 * 认证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());//自定义安全认证
	}

	/**
	 * 授权
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				//关闭CSRF
				.csrf().disable()
				//禁用session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				//权限不足处理
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
				.and()
				//认证失败处理
				.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
				.and()
				//动态url认证
				.authorizeRequests().anyRequest().access("@rbacAuthorityService.hasPermission(request, authentication)")
				.and()

				/* ------------登录配置------------ */

				//开启基于表单的登录
				.formLogin()
				//配置登录成功处理
				.successHandler(authenticationSuccessHandler)
				//配置登录失败处理
				.failureHandler(authenticationFailureHandler)
				.permitAll()
				.and()

				/* ------------注销配置------------ */

				.logout()
				//配置登出成功处理
				.logoutSuccessHandler(logoutSuccessHandler)
				.permitAll()
				.and()

				.rememberMe().rememberMeParameter("remember-me")//记住我
				.userDetailsService(userDetailService).tokenValiditySeconds(300)
				.and()
				.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);//JWT 认证

	}
}
