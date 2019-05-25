package com.ssm.mapper;

import com.ssm.pojo.SportData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SportDataMapper {
    int insert(SportData record);

    int insertSelective(SportData record);

    SportData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SportData record);

    int updateByPrimaryKey(SportData record);

    /**
     * 大屏
     * @param uid
     * @param startTime
     * @param endTime
     * @return
     */
    List<SportData> queryAllByUid(@Param(value = "uid")String uid, @Param(value = "startTime") Date startTime, @Param(value = "endTime")Date endTime);

    /**
     * 微信openid
     * @param openid
     * @param startTime
     * @param endTime
     * @return
     */
    List<SportData> queryWechatAllByOpenid(@Param(value = "openid")String openid, @Param(value = "startTime") Date startTime, @Param(value = "endTime")Date endTime);

    List<SportData> queryAll(@Param(value = "startTime") Date start, @Param(value = "endTime")Date endWeekDate);

    SportData queryByBaiduUid(@Param(value = "date")Date date, @Param(value = "baiduUid")String baiduUid);

    /**
     * 30天运动历史记录
     * @param openid
     * @return
     */
    List<SportData> selectThirtyDaysHistory(@Param(value = "openid")String openid);
}