package com.zcc.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.apachecommons.CommonsLog;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by ZhangChicheng on 2017/11/28.
 */
@Controller
@CommonsLog
public class HomeController {
	@GetMapping("/")
	@ApiIgnore
	public String login(HttpServletRequest request,
		Model model){
		return "login";
	}
}
