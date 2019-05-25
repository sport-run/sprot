package com.ssm.service;

import com.ssm.pojo.SportDateStatisticsLine;

import java.util.Date;
import java.util.List;

public interface SportDateStatisticsLineService {
    int insertSelective(SportDateStatisticsLine statisticsLine);

    SportDateStatisticsLine queryByBaiduUid(String userId);

    int updateSelective(SportDateStatisticsLine statisticsLine);
}
