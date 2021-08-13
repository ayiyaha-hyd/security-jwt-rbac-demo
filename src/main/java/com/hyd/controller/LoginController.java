package com.hyd.controller;

import com.alibaba.fastjson.JSONObject;
import com.hyd.bean.CommonResult;
import com.hyd.bean.LoginDTO;
import com.hyd.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class LoginController {
	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "/auth/login", method = RequestMethod.POST)
	public CommonResult login(@Valid LoginDTO loginDTO) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
		if (Objects.isNull(userDetails)) {
			return CommonResult.failure("用户不存在");
		}
		String jwtToken = JwtTokenUtil.generateToken(userDetails.getUsername(), 300, "_secret");
		JSONObject data = new JSONObject();
		data.put("jwtToken", jwtToken);
		return CommonResult.success("登录成功！", data);
	}
}
