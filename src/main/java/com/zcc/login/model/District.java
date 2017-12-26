package com.zcc.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/30.
 */
@Data
public class District {
	private Integer districtId;
	@JsonProperty("district_name")
	private String districtName;
	private String districtPinyinName;
	private Integer cityId;
}
