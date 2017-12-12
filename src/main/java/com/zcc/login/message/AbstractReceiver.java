package com.zcc.login.message;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by ZhangChicheng on 2017/12/8.
 */
public abstract class AbstractReceiver extends JedisPubSub {
	private String channel;

	public AbstractReceiver(String channel) {
		this.channel = channel;
	}

	public void register(Jedis jedis) {
		new Thread(()-> jedis.subscribe(this, channel)).start();
	}

	public String getChannel() {
		return this.channel;
	}

	@Override
	public void onMessage(String channel, String message) {

	}
}
