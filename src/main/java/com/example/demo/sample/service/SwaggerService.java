package com.example.demo.sample.service;

import com.example.demo.sample.dao.SwaggerDao;
import com.example.demo.sample.dto.SampleDto;

import com.example.demo.sample.mapper.SampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SwaggerService {

    @Autowired
    private SwaggerDao swaggerDao;

    @Autowired
    private SampleMapper sampleMapper;

    public int name(String name) throws Exception {
        return swaggerDao.name(name);
    }

    public List<SampleDto> names() throws SQLException {
        return swaggerDao.names();
    }

    public List<SampleDto> getNames() throws SQLException {
        return sampleMapper.getNames();
    }

    public int setNames() throws Exception {
        int result = 0;
        sampleMapper.setNames();

        if (1==1) {
            throw new Exception();
            //result = sampleMapper.setNames();
        }
        return result;
    }
}
