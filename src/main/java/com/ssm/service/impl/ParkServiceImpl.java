package com.ssm.service.impl;

import com.ssm.mapper.ParkMapper;
import com.ssm.pojo.Park;
import com.ssm.service.ParkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ParkServiceImpl implements ParkService {
    @Resource
    private ParkMapper mapper;
    @Override
    public Park queryParkById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
}
