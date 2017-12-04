package com.zcc.login.mapper.linkhome;

import java.util.List;

import com.zcc.login.model.Area;
import com.zcc.login.model.City;
import com.zcc.login.model.District;
import com.zcc.login.model.House;
import com.zcc.login.vo.HouseSelectRequest;

/**
 * Created by ZhangChicheng on 2017/11/30.
 */
public interface HouseSelectMapper {
	List<House> selectHouses(HouseSelectRequest request);

	Area getAreaById(Integer areaId);

	City getCityById(Integer cityId);

	District getDistrictById(Integer districtId);
}
