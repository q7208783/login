package com.zcc.login.message;

/**
 * Created by ZhangChicheng on 2017/12/8.
 */
public abstract class AbstractReceiver implements Receiver{
	private String channel;

	public AbstractReceiver(String channel){
		this.channel = channel;
	}

	@Override
	public String getChannel() {
		return this.channel;
	}
}
