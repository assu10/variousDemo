package com.example.demo.sample.dao;

import com.example.demo.sample.core.service.JdbcMasterService;
import com.example.demo.sample.core.service.JdbcSlaveService;
import com.example.demo.sample.dto.SampleDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SwaggerDao {

    @Autowired
    private JdbcMasterService jdbcMasterService;

    @Autowired
    private JdbcSlaveService jdbcSlaveService;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    public int name(String name) throws Exception {
        int result;

        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO got2_test.tbl_api(name) VALUES ");
        query.append("('").append(name).append("')");

        try {
            result = jdbcMasterService.queryForInsertUpdate(query.toString());
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public List<SampleDto> names() throws SQLException {
        ArrayList<Map> result;

        StringBuffer query = new StringBuffer();
        query.append("SELECT seq as SEQ, name AS name, regDate AS regDate FROM got2_test.tbl_api");

        try {
            result = jdbcSlaveService.queryForSelectList(query.toString());
        } catch (Exception e) {
            throw e;
        }

        return mapper.convertValue(result, new TypeReference<ArrayList<SampleDto>>(){});
    }
}
