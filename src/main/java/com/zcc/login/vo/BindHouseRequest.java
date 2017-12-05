package com.zcc.login.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/12/5.
 */
@Data
public class BindHouseRequest {
	@ApiModelProperty(hidden = true)
	private int userId;
	@ApiModelProperty(hidden = true)
	private String userEmail;
	@ApiModelProperty(hidden = true)
	private String phoneNum;
	private List<Integer> districtIds;
	private Double priceFrom;
	private Double priceTo;
	private Double sizeFrom;
	private Double sizeTo;
	private List<String> structures;
	private Double unitPriceFrom;
	private Double unitPriceTo;
	private String haveElevator;
}
