package com.ssm.mapper;

import com.ssm.pojo.SportDateStatisticsLine;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SportDateStatisticsLineMapper {
    int insert(SportDateStatisticsLine record);

    int insertSelective(SportDateStatisticsLine record);

    SportDateStatisticsLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SportDateStatisticsLine record);

    int updateByPrimaryKey(SportDateStatisticsLine record);

    List<SportDateStatisticsLine> queryAll(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    SportDateStatisticsLine queryByBaiduUid(@Param("baiduUid")String baiduUid);
}