package com.example.demo.sample.controller;

import com.example.demo.sample.dto.SampleDto;
import com.example.demo.sample.service.SwaggerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jboss.logging.Logger;
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
import java.util.HashMap;
import java.util.List;

@Api(description = "api 테스트 컨트롤러")
@RestController
@RequestMapping(value = "/sample/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SwaggerController {

    final private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SwaggerService swaggerService;

    @ApiOperation(value = "샘플1 API")
    @GetMapping(value = "/main/{area}")
    public SampleDto main(@PathVariable String area
            , @RequestParam String param1
            , @RequestHeader("remoteAddr") String remoteAddr) {

        SampleDto resultMain = new SampleDto();
        resultMain.setName(param1);

        return resultMain;
    }

    @ApiOperation(value = "샘플2 API(VO GET)", notes = "vo 리턴하는 API")
    @RequestMapping(value="put", method = RequestMethod.PUT)
    public SampleDto swaggerSetTest(@ApiParam(required = true, name="name", value="SET")
                              @PathVariable String name) {
        SampleDto vo = new SampleDto();
        vo.setName(name);

        return vo;
    }

    @ApiOperation(value = "샘플3 API(VO SET)", notes = "vo 리턴하는 API")
    @RequestMapping(value="set", method = RequestMethod.GET)
    public SampleDto swaggerGetTest(@ApiParam(required = true, name="name", value="GET")
                                 @PathVariable String name) {
        SampleDto vo = new SampleDto();
        vo.setName(name);

        return vo;
    }

    @ApiOperation(value = "샘플4 API(DB SET)", notes = "DB 저장하는 API - JDBC")
    @RequestMapping(value="name", method = RequestMethod.PUT)
    public int name(@ApiParam(required = true, name="reqName", value="juhyun")
                    @RequestParam String reqName) throws Exception {
        return swaggerService.name(reqName);
    }

    @ApiOperation(value = "샘플5 API(DB GET)", notes = "DB 조회하는 API - JDBC")
    @RequestMapping(value="name", method = RequestMethod.GET)
    public List<SampleDto> names(HttpServletRequest req) throws SQLException {
        return swaggerService.names();
    }

    @ApiOperation(value = "샘플6 API(DB GET)", notes = "DB 조회하는 API - mybatis")
    @RequestMapping(value="getNames", method = RequestMethod.GET)
    public List<SampleDto> getNames(HttpServletRequest req) throws SQLException {
        return swaggerService.getNames();
    }

    @ApiOperation(value = "샘플7 API(DB SET)", notes = "DB 저장하는 API - mybatis")
    @RequestMapping(value="setNames", method = RequestMethod.PUT)
    public int setNames(@ApiParam(required = true, name="reqName", value="주현")
                            @RequestParam HashMap<String, Object> reqMap) throws Exception {
        return swaggerService.setNames(reqMap);
    }
}
