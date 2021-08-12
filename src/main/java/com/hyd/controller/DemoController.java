package com.hyd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {
	@GetMapping("index")
	public String index(){
		return "index";
	}
	@GetMapping("inner")
	public String inner(){
		return "inner";
	}
}
