package com.zcc.login.vo;

import java.util.List;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/12/6.
 */
@Data
public class SendMailRequest {
	private List<String> receiversAddr;
	private String title;
	private String content;
}
