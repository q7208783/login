package com.zcc.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.common.utils.EmailSender;
import com.zcc.login.common.utils.PropertyMessageUtils;
import com.zcc.login.service.HouseService;
import com.zcc.login.vo.BindHouseRequest;
import com.zcc.login.vo.CommonResponse;
import com.zcc.login.vo.SendMailRequest;

/**
 * Created by ZhangChicheng on 2017/12/6.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private EmailSender emailSender;
	@Autowired
	private HouseService houseService;

	@PostMapping("/sendEmail")
	@ResponseBody
	public CommonResponse<String> sendEmail(@RequestBody SendMailRequest sendMailRequest)throws ServiceException {
		emailSender.send(sendMailRequest.getReceiversAddr(),sendMailRequest.getTitle(),sendMailRequest.getContent());
		return new CommonResponse<>(PropertyMessageUtils.getMessage("common.message.1"));
	}

	@GetMapping("/allBinds")
	@ResponseBody
	public CommonResponse<List<BindHouseRequest>> getAllBindCondition()throws ServiceException{
		List<BindHouseRequest> bindHouseRequests = houseService.selectAllCondition();
		return new CommonResponse<>(bindHouseRequests);
	}
}
