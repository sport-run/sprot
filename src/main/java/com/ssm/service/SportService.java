package com.ssm.service;

import com.ssm.commons.bean.ResultBean;
import com.ssm.params.CameraParam;
import com.ssm.params.EditUserParam;
import com.ssm.params.PutImageParam;
import com.ssm.vo.SportDetailsVo;
import com.ssm.vo.SportListItemVo;
import com.ssm.vo.WeatherVo;

import java.util.List;

public interface SportService {

    List<SportListItemVo> getSportList();

    WeatherVo getSportWeather();

    ResultBean getSportPutImage(PutImageParam putImageParam);

    /**
     * 个人运动数据
     * @param uid
     * @return
     */
    SportDetailsVo getSportDetails(String uid);

    ResultBean editUser(EditUserParam param);

    ResultBean getPhoneCode(String phone);

    ResultBean getCameraImage(CameraParam cameraParam);
}
