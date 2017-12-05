package com.zcc.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.model.House;
import com.zcc.login.model.User;
import com.zcc.login.service.HouseService;
import com.zcc.login.vo.BindHouseRequest;
import com.zcc.login.vo.CommonResponse;
import com.zcc.login.vo.HouseSelectRequest;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by ZhangChicheng on 2017/12/1.
 */
@Controller
@RequestMapping("/house")
public class HouseController {
	@Autowired
	private HouseService houseService;

	@GetMapping("/queryHouses")
	@ResponseBody
	public CommonResponse<List<House>> queryHouses(HouseSelectRequest request) throws ServiceException {
		List<House> houses = houseService.selectHouses(request);
		CommonResponse<List<House>> commonResponse = new CommonResponse(houses);
		commonResponse.setPageInfo(request.getPageInfo());
		return commonResponse;
	}

	@PostMapping("/bind")
	@ResponseBody
	public CommonResponse<Boolean> bindHouse(@ApiIgnore @RequestAttribute("user") User user,
		@RequestBody BindHouseRequest request) throws ServiceException {
		request.setUserId(user.getUserId());
		request.setUserEmail(user.getEmail());
		request.setPhoneNum(user.getPhoneNum());
		return new CommonResponse(houseService.bindHouseCondition(request));
	}

	@PostMapping("/unbind")
	@ResponseBody
	public CommonResponse<Boolean> unbindHouse(@ApiIgnore @RequestAttribute("user") User user) throws ServiceException {
		return new CommonResponse<>(houseService.deleteBindHouse(user.getUserId()));
	}

	@GetMapping("/getBindInfo")
	@ResponseBody
	public CommonResponse<BindHouseRequest> getBindInfo(@ApiIgnore @RequestAttribute("user") User user) throws
		ServiceException {
		return new CommonResponse<>(houseService.queryHouseCondition(user.getUserId()));
	}
}
