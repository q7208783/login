package com.zcc.login.mapper.linkhome;

import java.util.List;

import com.zcc.login.dto.BindHouseDto;
import com.zcc.login.model.Area;
import com.zcc.login.model.City;
import com.zcc.login.model.District;
import com.zcc.login.model.House;
import com.zcc.login.vo.BindHouseRequest;
import com.zcc.login.vo.HouseSelectRequest;
import com.zcc.login.vo.NotificationRequest;

/**
 * Created by ZhangChicheng on 2017/11/30.
 */
public interface HouseSelectMapper {
	List<House> selectHouses(HouseSelectRequest request);

	Area getAreaById(Integer areaId);

	City getCityById(Integer cityId);

	District getDistrictById(Integer districtId);

	Boolean bindHouseCondition(BindHouseDto request);

	BindHouseDto queryHouseCondition(Integer userId);

	List<BindHouseDto> selectAllCondition();

	Boolean updateBindHouse (BindHouseDto request);

	Boolean deleteBindHouse (Integer userId);
}
