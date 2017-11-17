package com.zcc.login.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/17.
 */
@Data
public class ChangePasswordRequest {
	private int userId;
	@NotNull
	private String userName;
	@NotNull
	private String oldPwd;
	@NotNull
	private String newPwd;

	private String lastrResetPwYmdt;
}
