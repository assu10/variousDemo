package com.example.demo.sample.controller;

import com.example.demo.sample.dto.SampleDto;
import com.example.demo.sample.service.SwaggerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Api(description = "api 테스트 컨트롤러")
@RestController
@RequestMapping(value = "/sample/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SwaggerController {

    @Autowired
    SwaggerService swaggerService;


    @ApiOperation(value = "Set Api Operation", notes = "샘플 응답 URL")
    //@RequestMapping(value="{name}", method = RequestMethod.PUT)
    @RequestMapping(value="put", method = RequestMethod.PUT)
    public SampleDto swaggerSetTest(@ApiParam(required = true, name="name", value="SET")
                              @PathVariable String name) {
        SampleDto vo = new SampleDto();
        vo.setName(name);

        return vo;
    }

    @ApiOperation(value = "Get Api Operation", notes = "샘플 응답 URL")
    @RequestMapping(value="set", method = RequestMethod.GET)
    public SampleDto swaggerGetTest(@ApiParam(required = true, name="name", value="GET")
                                 @PathVariable String name) {
        SampleDto vo = new SampleDto();
        vo.setName(name);

        return vo;
    }

    @ApiOperation(value = "메인 페이지")
    @GetMapping(value = "/main/{area}")
    public SampleDto main(@PathVariable String area
            , @RequestParam String param1
            , @RequestHeader("remoteAddr") String remoteAddr) {

        SampleDto resultMain = new SampleDto();
        resultMain.setName(param1);

        return resultMain;
    }

    @ApiOperation(value = "DB SET API", notes = "DB 저장")
    @RequestMapping(value="name", method = RequestMethod.PUT)
    public int name(@ApiParam(required = true, name="reqName", value="juhyun")
                    @RequestParam String reqName) throws Exception {
        return swaggerService.name(reqName);
    }

    @ApiOperation(value = "DB GET API", notes = "DB 조회")
    @RequestMapping(value="name", method = RequestMethod.GET)
    public List<SampleDto> names(HttpServletRequest req) throws SQLException {
        return swaggerService.names();
    }
}
