package com.zcc.login.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/12/5.
 */
@Data
public class BindHouseDto {
	private int userId;
	private String userEmail;
	private String phoneNum;
	private String districtIds;
	private Double priceFrom;
	private Double priceTo;
	private Double sizeFrom;
	private Double sizeTo;
	private String structures;
	private Double unitPriceFrom;
	private Double unitPriceTo;
	private String haveElevator;
}
