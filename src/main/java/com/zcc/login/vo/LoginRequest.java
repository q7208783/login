package com.zcc.login.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/15.
 */
@Data
public class LoginRequest {
	@NotNull
	private String username;
	@NotNull
	private String password;
}

