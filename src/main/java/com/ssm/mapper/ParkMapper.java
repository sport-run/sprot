package com.ssm.mapper;

import com.ssm.pojo.Park;

public interface ParkMapper {
    int insert(Park record);

    int insertSelective(Park record);

    Park selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Park record);

    int updateByPrimaryKey(Park record);
}