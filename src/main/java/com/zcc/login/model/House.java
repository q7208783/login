package com.zcc.login.model;

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
	private Double size;
	private String address;
	private String structure;
	private String url;
	private String orient;
	private String elevator;
	private Integer attentionNum;
	private Integer visitNum;
	private Double unitPrice;
	private String createDate;
	private Area area;
	private District district;
	private City city;
}
