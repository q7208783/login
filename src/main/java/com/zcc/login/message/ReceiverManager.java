package com.zcc.login.message;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by ZhangChicheng on 2017/12/12.
 */
public class ReceiverManager {
	private JedisPool jedisPool;
	private List<AbstractReceiver> receivers = new ArrayList<>();
	private Jedis jedis;

	public ReceiverManager(JedisPool jedisPool){
		this.jedisPool = jedisPool;
		this.jedis = jedisPool.getResource();
	}

	public void startHandleMessage(){
		if(jedis==null){
			this.jedis = jedisPool.getResource();
		}
		for (AbstractReceiver receiver:receivers){
			receiver.register(this.jedis);
		}
	}

	public void addReceiver(AbstractReceiver receiver){
		receivers.add(receiver);
	}

	public void removeReceiver(AbstractReceiver receiver){
		receivers.remove(receiver);
	}
}
