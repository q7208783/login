package com.zcc.login.model;

import java.util.List;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Data
public class User {
	private String user_id;
	private String user_name;
	private String user_pwd;
	private String user_phone_num;
	private List<Authority> authorities;
	private String user_mail;
}
