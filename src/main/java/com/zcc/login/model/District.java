package com.zcc.login.model;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/30.
 */
@Data
public class District {
	private int districtId;
	private String districtName;
	private String districtPinyinName;
	private City city;
}
