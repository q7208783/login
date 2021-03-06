package com.zcc.login.service;

import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.dto.BindHouseDto;
import com.zcc.login.model.Area;
import com.zcc.login.model.City;
import com.zcc.login.model.District;
import com.zcc.login.model.House;
import com.zcc.login.vo.BindHouseRequest;
import com.zcc.login.vo.HouseSelectRequest;
import com.zcc.login.vo.NotificationRequest;

/**
 * Created by ZhangChicheng on 2017/12/1.
 */
public interface HouseService {
	List<House> selectHouses(HouseSelectRequest request) throws ServiceException;

	Area getAreaById(Integer areaId) throws ServiceException;

	Integer getAreaByName(String areaName)throws ServiceException;

	District getDistrictById(Integer districtId) throws ServiceException;

	Integer getDistrictByName(String districtName)throws ServiceException;

	City getCityById(Integer cityId) throws ServiceException;

	Integer getCityByName(String cityName)throws ServiceException;

	Boolean bindHouseCondition(BindHouseRequest request) throws ServiceException;

	BindHouseRequest queryHouseCondition(Integer userId) throws ServiceException;

	List<BindHouseRequest> selectAllCondition() throws ServiceException;

	Boolean deleteBindHouse(Integer userId) throws ServiceException;
}
