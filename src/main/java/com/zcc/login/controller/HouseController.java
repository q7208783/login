package com.zcc.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.model.House;
import com.zcc.login.service.HouseService;
import com.zcc.login.vo.CommonResponse;
import com.zcc.login.vo.HouseSelectRequest;

/**
 * Created by ZhangChicheng on 2017/12/1.
 */
@Controller
@RequestMapping("/house")
public class HouseController {
	@Autowired
	private HouseService houseService;

	@GetMapping("/query")
	@ResponseBody
	public CommonResponse<List<House>> queryHouses(HouseSelectRequest request)throws ServiceException{
		List<House> houses = houseService.selectHouses(request);
		return new CommonResponse<>(houses);
	}
}
