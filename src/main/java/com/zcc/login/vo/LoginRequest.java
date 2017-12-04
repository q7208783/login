package com.zcc.login.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/15.
 */
@Data
public class LoginRequest {
    @NotNull
    @ApiModelProperty(example = "test", required = true)
    private String username;
    @NotNull
    @ApiModelProperty(example = "123456", required = true)
    private String password;
}

