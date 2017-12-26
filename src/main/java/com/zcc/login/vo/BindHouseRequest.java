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
	private List<Integer> structures;
	private Double unitPriceFrom;
	private Double unitPriceTo;
	private String haveElevator;

	public boolean atPriceScale(Double price) {
		return (this.priceFrom == null ? true : (price > this.priceFrom)) &&
			(this.priceTo == null ? true : (price < this.priceTo));
	}

	public boolean atUnitScale(Double unitPrice) {
		return (this.unitPriceFrom == null ? true : (unitPrice > this.unitPriceFrom)) &&
			(this.unitPriceTo == null ? true : (unitPrice < this.unitPriceTo));
	}

	public boolean atSizeScale(Double size) {
		return (this.sizeFrom == null ? true : (size > this.sizeFrom)) &&
			(this.sizeTo == null ? true : (size < this.sizeTo));
	}

	public boolean atDistrictIdScale(Integer districtId) {
		return this.districtIds == null ? true : districtIds.contains(districtId);
	}

	public boolean haveElevator(String haveElevator){
		return this.haveElevator==null?true:(haveElevator.equals(this.haveElevator));
	}

	public boolean atStructureScale(Integer structure){
		return this.structures==null?true:(structures.contains(structure));
	}
}
