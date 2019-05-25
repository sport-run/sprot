package com.ssm.mapper;

import com.ssm.pojo.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    /*

     */
    User selectBybaiduUid(String baiduUid);


    /**
     * 根据Openid用户获取信息
     * @param baiduUid
     * @return
     */
    User selectUserByOpenid(String baiduUid);


    /**
     * 按照openid 更新
     * @param record
     * @return
     */
    int updateByOpenidSelective(User record);
}