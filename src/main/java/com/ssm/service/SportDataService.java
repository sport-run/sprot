package com.ssm.service;

import com.ssm.pojo.SportData;
import com.ssm.vo.SportDetailsVo;
import com.ssm.vo.SportWechatDetailsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SportDataService {
    SportDetailsVo getSportDetails(String uid);

    /**
     * 更新
     * @param sportData
     * @return
     */
    int updateByPrimaryKeySelective(SportData sportData);
    /**
     * 排行榜 20
     * @return
     */
    List<SportData> getSportWeekList();

    SportData queryByBaiduUid(String baiduUid);

    int insertSelective(SportData sportData);

    /**
     * 30天运动历史记录
     * @param openid
     * @return
     */
    List<SportData> selectThirtyDaysHistory(@Param(value = "openid")String openid);


    /**
     * 微信查询跑步信息
     * @param openid
     * @return
     */
    SportWechatDetailsVo getSportDetailsByOpenid(String openid);
}
