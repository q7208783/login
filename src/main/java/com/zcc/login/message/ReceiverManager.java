package com.zcc.login.message;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.apachecommons.CommonsLog;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by ZhangChicheng on 2017/12/12.
 */
@CommonsLog
public class ReceiverManager {
	private JedisPool jedisPool;
	private List<AbstractReceiver> receivers = new ArrayList<>();
	private Jedis jedis;

	public ReceiverManager(JedisPool jedisPool){
		this.jedisPool = jedisPool;
		this.jedis = jedisPool.getResource();
	}

	public void startHandleMessage(){
		log.debug("receivers contains :" + receivers.toString());
		if(jedis==null){
			this.jedis = jedisPool.getResource();
		}
		for (AbstractReceiver receiver:receivers){
			receiver.register(this.jedis);
			log.debug("receiver isSubscribed :" + receiver.isSubscribed());
		}
	}

	public void addReceiver(AbstractReceiver receiver){
		receivers.add(receiver);
	}

	public void removeReceiver(AbstractReceiver receiver){
		receivers.remove(receiver);
	}
}
