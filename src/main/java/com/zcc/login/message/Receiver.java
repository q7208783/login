package com.zcc.login.message;

/**
 * Created by ZhangChicheng on 2017/12/7.
 */
public interface Receiver {
	String getChannel();

	void handleMessage(String channel, String message);
}
