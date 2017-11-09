package com.zcc.login.vo;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
@Data
public class CreateUserRequest {
	private String userName;
	private String password;
	private String phoneNum;
	private String email;
}
