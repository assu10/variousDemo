package com.example.demo.sample.service;

import com.example.demo.sample.dao.SwaggerDao;
import com.example.demo.sample.dto.SampleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SwaggerService {

    @Autowired
    private SwaggerDao swaggerDao;

    public int name(String name) throws Exception {
        return swaggerDao.name(name);
    }

    public List<SampleDto> names() throws SQLException {
        return swaggerDao.names();
    }
}