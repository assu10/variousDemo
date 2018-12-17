package com.example.demo.sample.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel(value = "Swagger Test VO")
public class SampleDto {

    @ApiModelProperty(value = "시퀀스")
    private int seq;

    @ApiModelProperty(value = "이름", example = "juhyun10")
    private String name;

    @ApiModelProperty(value = "날짜", example = "201812141133")
    private String regDate;
}
