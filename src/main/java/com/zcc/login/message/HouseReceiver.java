package com.zcc.login.message;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.common.utils.EmailSender;

/**
 * Created by ZhangChicheng on 2017/12/8.
 */
@Component
public class HouseReceiver extends AbstractReceiver{

	@Autowired
	private EmailSender emailSender;

	private static final String HOUSE_CHANNEL = "HouseChannel";

	public HouseReceiver() {
		super(HOUSE_CHANNEL);
	}

	@Override
	public void handleMessage(String channel, String message) {
		try {
			JSONObject jsonObject = new JSONObject(message);
			String url = (String)jsonObject.get("url");
			List<String> subUserEmailAddrs = new ArrayList<>();
			subUserEmailAddrs.add("576926338@qq.com");
			emailSender.send(subUserEmailAddrs,"house fond",url);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
