package com.zcc.login.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by ZhangChicheng on 2017/12/8.
 */
@Component
public class RedisMQHandler extends JedisPubSub {

	private List<Receiver> receivers;
	private List<String> channels;

	public RedisMQHandler() {
		receivers = new ArrayList<>();
		channels = new ArrayList<>();
	}

	public List<String> getChannels(){
		return this.channels;
	}

	public void bind(Receiver receiver) {
		receivers.add(receiver);
		channels.add(receiver.getChannel());
	}

	public void unbind(Receiver receiver) {
		receivers.remove(receiver);
		channels.remove(receiver.getChannel());
	}

	@Override
	public void onMessage(String channel, String message) {
		for (Receiver receiver : receivers) {
			if (receiver.getChannel().equals(channel)) {
				receiver.handleMessage(channel, message);
			}
		}
	}
}
