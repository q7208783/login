package com.zcc.login.model;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/30.
 */
@Data
public class Area {
	private Integer areaId;
	private String areaName;
	private String areaPinyinName;
	private Integer cityId;
	private Integer districtId;
}