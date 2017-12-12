package com.zcc.login.message;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zcc.login.common.utils.EmailSender;
import com.zcc.login.common.utils.JsonUtil;
import com.zcc.login.model.House;
import com.zcc.login.service.HouseService;
import com.zcc.login.vo.BindHouseRequest;

/**
 * Created by ZhangChicheng on 2017/12/8.
 */
@Component
public class HouseReceiver extends AbstractReceiver {

	@Autowired
	private EmailSender emailSender;
	@Autowired
	private HouseService houseService;

	private static final String HOUSE_CHANNEL = "HouseChannel";

	public HouseReceiver() {
		super(HOUSE_CHANNEL);
	}

	@Override
	public void onMessage(String channel, String message) {
		super.onMessage(channel, message);
		try {
			House house = (House)JsonUtil.toObject(message, House.class);
			JSONObject jsonObject = new JSONObject(message);
			String districtName = (String)jsonObject.get("squre");
			String areaName = (String)jsonObject.get("area_name");

			//TODO this two maybe will be null
			Integer areaId = houseService.getAreaByName(areaName);
			Integer districtId = houseService.getDistrictByName(districtName);
			house.setDistrictId(districtId);
			house.setAreaId(areaId);
			List<BindHouseRequest> bindList = houseService.selectAllCondition();
			List<String> subUserEmailAddrs = getSendEmailUserList(house, bindList);

			emailSender.send(subUserEmailAddrs, "找到一个合适的房子："+house.getTitle(), house.getUrl());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<String> getSendEmailUserList(House house, List<BindHouseRequest> bindList) {
		List<String> emailAddrs = new ArrayList<>();
		for (BindHouseRequest bind : bindList) {
			if (matchBindCondition(bind, house)) {
				emailAddrs.add(bind.getUserEmail());
			}
		}
		return emailAddrs;
	}

	private boolean matchBindCondition(BindHouseRequest bind, House house) {
		Double price = house.getPrice();
		Double size = house.getSize();
		Integer structure = getRoomNum(house.getStructure());
		Integer districtId = house.getDistrictId();
		Double unitPrice = house.getUnitPrice();
		String haveElevator = house.getElevator();

		return bind.atDistrictIdScale(districtId) && bind.atPriceScale(price) && bind.atSizeScale(size)
			&& bind.atUnitScale(unitPrice) && bind.haveElevator(haveElevator) && bind.atStructureScale(structure);
	}

	private Integer getRoomNum(String structrue) {
		return Integer.parseInt(String.valueOf(structrue.charAt(structrue.indexOf('室')-1)));
	}
}
