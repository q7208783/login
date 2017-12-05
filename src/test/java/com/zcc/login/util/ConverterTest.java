package com.zcc.login.util;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zcc.login.common.converter.HouseConverter;
import com.zcc.login.dto.BindHouseDto;
import com.zcc.login.vo.BindHouseRequest;

/**
 * Created by ZhangChicheng on 2017/12/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConverterTest {

	@Autowired
	HouseConverter houseConverter;

	@Test
	public void bindHouseConverterTest(){
		BindHouseRequest request = new BindHouseRequest();
		List<Integer> districtIds= new ArrayList<>();
		districtIds.add(1);
		districtIds.add(2);
		districtIds.add(3);
		List<String> structures=new ArrayList<>();
		structures.add("3室");
		structures.add("2室");
		structures.add("1室");
		request.setDistrictIds(districtIds);
		request.setStructures(structures);
		BindHouseDto bindHouseDto = houseConverter.bindHouseConverterDto(request);
		BindHouseRequest request1 = houseConverter.bindHouseConverterReq(bindHouseDto);
		assertTrue(request.getDistrictIds().size()==request1.getDistrictIds().size());
		assertTrue(request.getStructures().size()==request1.getStructures().size());
		request1.getStructures().stream().forEach(string->assertTrue(request.getStructures().contains(string)));
		request1.getDistrictIds().stream().forEach(integer->assertTrue(request.getDistrictIds().contains(integer)));
	}
}
