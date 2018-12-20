package com.example.demo.sample.mapper;

import com.example.demo.sample.dto.SampleDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SampleMapper {

    List<SampleDto> getNames();
    int setNames(HashMap<String, Object> reqMap);
}
