package com.zcc.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/30.
 */
@Data
public class House {
	private Integer houseId;
	private String title;
	private Integer cityId;
	private Integer districtId;
	private Integer areaId;
	private Double price;
	@JsonProperty("house_size")
	private Double size;
	private String address;
	@JsonProperty("house_structure")
	private String structure;
	private String url;
	@JsonProperty("house_orient")
	private String orient;
	@JsonProperty("house_elevator")
	private String elevator;
	private Integer attentionNum;
	@JsonProperty("visit_num")
	private Integer visitNum;
	private Double unitPrice;
	private String createDate;
	private Area area;
	private District district;
	private City city;
}
