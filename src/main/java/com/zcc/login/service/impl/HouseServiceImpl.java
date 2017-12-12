package com.zcc.login.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcc.login.cache.CacheOperation;
import com.zcc.login.common.annotation.DataSourceType;
import com.zcc.login.common.constant.DataSourceEnum;
import com.zcc.login.common.converter.HouseConverter;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.dto.BindHouseDto;
import com.zcc.login.mapper.linkhome.HouseSelectMapper;
import com.zcc.login.model.Area;
import com.zcc.login.model.City;
import com.zcc.login.model.District;
import com.zcc.login.model.House;
import com.zcc.login.service.HouseService;
import com.zcc.login.service.UserService;
import com.zcc.login.vo.BindHouseRequest;
import com.zcc.login.vo.HouseSelectRequest;
import com.zcc.login.vo.NotificationRequest;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Created by ZhangChicheng on 2017/12/1.
 */
@Service
@DataSourceType(DataSourceEnum.LINK_HOME)
@CommonsLog
@SuppressWarnings("SpringJavaAutowiringInspection")
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseSelectMapper houseSelectMapper;
	@Autowired
	private HouseConverter houseConverter;
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public List<House> selectHouses(HouseSelectRequest request) throws ServiceException {
		return houseSelectMapper.selectHouses(request);
	}

	@Override
	public Area getAreaById(Integer areaId) throws ServiceException {
		return houseSelectMapper.getAreaById(areaId);
	}

	@Override
	public District getDistrictById(Integer districtId) throws ServiceException {
		return houseSelectMapper.getDistrictById(districtId);
	}

	@Override
	public City getCityById(Integer cityId) throws ServiceException {
		return houseSelectMapper.getCityById(cityId);
	}

	@Override
	@Transactional
	@CacheOperation(keyValue = "BindHouseList",method = "put")
	public Boolean bindHouseCondition(BindHouseRequest request) throws ServiceException {
		BindHouseDto bindHouseDto = houseConverter.bindHouseConverterDto(request);
		if (!houseSelectMapper.updateBindHouse(bindHouseDto))
			return houseSelectMapper.bindHouseCondition(bindHouseDto);
		return Boolean.TRUE;
	}

	@Override
	public BindHouseRequest queryHouseCondition(Integer userId) throws ServiceException {
		BindHouseDto bindHouseDto = houseSelectMapper.queryHouseCondition(userId);
		NotificationRequest notificationRequest = userService.getNotificationInfo(userId);
		bindHouseDto.setPhoneNum(notificationRequest.getPhoneNum());
		bindHouseDto.setUserEmail(notificationRequest.getEmail());
		return houseConverter.bindHouseConverterReq(bindHouseDto);
	}

	@Override
	public List<BindHouseRequest> selectAllCondition() throws ServiceException {
		List<BindHouseDto> bindHouseDtos = houseSelectMapper.selectAllCondition();
		return bindHouseDtos.stream().map(bindHouseDto -> {
			try{
				NotificationRequest notificationRequest = userService.getNotificationInfo(bindHouseDto.getUserId());
				bindHouseDto.setPhoneNum(notificationRequest.getPhoneNum());
				bindHouseDto.setUserEmail(notificationRequest.getEmail());
			}catch (ServiceException e){
				log.error("getNotificationInfo got error", e);
			}
			return houseConverter.bindHouseConverterReq(bindHouseDto);
		}).collect(Collectors.toList());
	}

	@Override
	public Boolean deleteBindHouse(Integer userId) throws ServiceException {
		return houseSelectMapper.deleteBindHouse(userId);
	}
}
