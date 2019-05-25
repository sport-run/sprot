package com.ssm.service.impl;

import com.ssm.mapper.SportDateStatisticsLineMapper;
import com.ssm.pojo.SportDateStatisticsLine;
import com.ssm.service.SportDateStatisticsLineService;
import com.ssm.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SportDateStatisticsLineServiceImpl implements SportDateStatisticsLineService {
    @Resource
    private SportDateStatisticsLineMapper statisticsLineMapper;
    @Override
    public int insertSelective(SportDateStatisticsLine statisticsLine) {
        return statisticsLineMapper.insertSelective(statisticsLine);
    }

    @Override
    public SportDateStatisticsLine queryByBaiduUid(String userId) {
        return statisticsLineMapper.queryByBaiduUid(userId);
    }

    @Override
    public int updateSelective(SportDateStatisticsLine statisticsLine) {
        return statisticsLineMapper.updateByPrimaryKeySelective(statisticsLine);
    }

}
