package com.ssm.service;

import com.ssm.commons.bean.ResultBean;
import com.ssm.params.EditUserParam;
import com.ssm.pojo.User;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface UserService {
    User getUserById(int id);
    ResultBean insertUser(User user);
    ResultBean updateUser(EditUserParam param);


    /**
     *
     * @param baiduUid
     * @return
     */
    User selectBybaiduUid(String baiduUid);


    /**
     * 微信公众号新增用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * openid 获取用户信息
     * @return
     */
    ResultBean getUserByOpenid(String penid);
}
