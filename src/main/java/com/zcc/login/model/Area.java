package com.zcc.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/30.
 */
@Data
public class Area {
	private Integer areaId;
	@JsonProperty("area_name")
	private String areaName;
	private String areaPinyinName;
	private Integer cityId;
	private Integer districtId;
}
