package com.zcc.login.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Data
public class User {
	private int userId;
	private String userName;
	private String password;
	private String phoneNum;
	private List<Authority> authorities;
	private String email;
	private String createTimeYmdt;
	private String lastrResetPwYmdt;

	public boolean addAuthority(Authority authority){
		if(authorities==null)
			authorities=new ArrayList<>();
		return authorities.add(authority);
	}
}
