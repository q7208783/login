package com.zcc.login.common.converter;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zcc.login.dto.BindHouseDto;
import com.zcc.login.vo.BindHouseRequest;

/**
 * Created by ZhangChicheng on 2017/12/5.
 */
@Component
public class HouseConverter {
	@Autowired
	private ModelMapper modelMapper;

	public BindHouseDto bindHouseConverterDto(BindHouseRequest request) {
		if (request == null)
			return null;
		BindHouseDto bindHouseDto = modelMapper.map(request, BindHouseDto.class);
		StringBuffer sb1 = new StringBuffer();
		request.getDistrictIds().stream().forEach(integer -> sb1.append(integer + "//"));
		bindHouseDto.setDistrictIds(sb1.toString());
		StringBuffer sb2 = new StringBuffer();
		request.getStructures().stream().forEach(structure -> sb2.append(structure + "//"));
		bindHouseDto.setStructures(sb2.toString());
		return bindHouseDto;
	}

	public BindHouseRequest bindHouseConverterReq(BindHouseDto bindHouseDto) {
		if (bindHouseDto == null)
			return null;
		BindHouseRequest bindHouseRequest = modelMapper.map(bindHouseDto, BindHouseRequest.class);
		List<String> structures = Arrays.stream(bindHouseDto.getStructures().split("//")).collect(toList());
		bindHouseRequest.setStructures(structures);
		List<Integer> districtsIds = Arrays.stream(bindHouseDto.getDistrictIds().split("//")).map(
			string -> Integer.valueOf(string)).collect(toList());
		bindHouseRequest.setDistrictIds(districtsIds);
		return bindHouseRequest;
	}
}
