package com.zcc.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcc.login.common.annotation.DataSourceType;
import com.zcc.login.common.constant.DataSourceEnum;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.mapper.linkhome.HouseSelectMapper;
import com.zcc.login.model.Area;
import com.zcc.login.model.City;
import com.zcc.login.model.District;
import com.zcc.login.model.House;
import com.zcc.login.service.HouseService;
import com.zcc.login.vo.HouseSelectRequest;

/**
 * Created by ZhangChicheng on 2017/12/1.
 */
@Service
@DataSourceType(DataSourceEnum.LINK_HOME)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseSelectMapper houseSelectMapper;

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
}
