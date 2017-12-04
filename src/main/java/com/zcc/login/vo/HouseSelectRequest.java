package com.zcc.login.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/12/1.
 */
@Data
public class HouseSelectRequest {
	private List<Integer> areaIds;
	@NotEmpty
	private List<Integer> districtIds;
	private Double priceFrom;
	private Double priceTo;
	private Double sizeFrom;
	private Double sizeTo;
	private List<String> structures;
	private Double unitPriceFrom;
	private Double unitPriceTo;
	private String haveElevator;
	private Integer pageSize;
	private Integer pageNo;
}
