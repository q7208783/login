package com.zcc.login.common.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.zcc.login.config.PasswordConfig;

/**
 * Created by ZhangChicheng on 2017/12/6.
 */
@Component
public class EmailSender {

	private SingleSendMailRequest request;
	private IClientProfile profile;
	private IAcsClient client;

	@Autowired
	private PasswordConfig passwordConfig;

	@PostConstruct
	public void init(){
		profile = DefaultProfile.getProfile("cn-hangzhou", passwordConfig.getAliyunAccessKeyId(), passwordConfig.getAliyunAccessSecret());
		client = new DefaultAcsClient(profile);
		request = new SingleSendMailRequest();
	}

	public void send(){
		try {
			request.setAccountName("batch@mail.zhangcc.group");
			request.setFromAlias("皮条张");
			request.setAddressType(1);
			request.setTagName("HomeLink");
			request.setReplyToAddress(true);
			request.setToAddress("576926338@qq.com");
			request.setSubject("Test");
			request.setHtmlBody("test1111");
			SingleSendMailResponse httpResponse = client.getAcsResponse(request);
		} catch (ServerException e) {
			e.printStackTrace();
		}
		catch (ClientException e) {
			e.printStackTrace();
		}
	}

}
