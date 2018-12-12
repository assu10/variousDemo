package com.example.demo.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "api 테스트 컨트롤러")
@RestController
public class SwaggerTestController {

    @ApiOperation(value = "샘플 조회 Api Operation", notes = "샘플 응답 URL")
    @RequestMapping(value="/sample/{sampleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public testVO swaggerTest(@ApiParam(required = true, name="sampleId", value="조회할 샘플 아이디")
                              @PathVariable String sampleId) {
        testVO vo = new testVO();
        vo.setSampleId(sampleId);

        return vo;
    }


    @ApiOperation(value = "메인 페이지")
    @GetMapping(value = "/main/{area}")
    public testVO main(@PathVariable String area
            , @RequestParam String param1
            , @RequestHeader("remoteAddr") String remoteAddr) {

        testVO resultMain = new testVO();
        resultMain.setSampleId(param1);
        resultMain.setSampleNm(area);

        return resultMain;
    }
}
