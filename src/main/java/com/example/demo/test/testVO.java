package com.example.demo.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true, includeFieldNames = true)
@ApiModel(value = "Swagger Test VO")
public class testVO {
    @ApiModelProperty(value = "샘플id", example = "juhyun10")
    private String sampleId;

    @ApiModelProperty(value = "샘플name", example = "주현")
    private String sampleNm;
}
