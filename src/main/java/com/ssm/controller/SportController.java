package com.ssm.controller;

import com.alibaba.druid.util.StringUtils;
import com.ssm.commons.bean.ResultBean;
import com.ssm.commons.constants.Codes;
import com.ssm.commons.utils.ResultBeanUtils;
import com.ssm.params.CameraParam;
import com.ssm.params.EditUserParam;
import com.ssm.params.PutImageParam;
import com.ssm.pojo.SportData;
import com.ssm.service.SportDataService;
import com.ssm.service.SportService;
import com.ssm.vo.SportDetailsVo;
import com.ssm.vo.SportListItemVo;
import com.ssm.vo.SportWechatDetailsVo;
import com.ssm.vo.WeatherVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/p1/api/sport")
public class SportController {
    @Autowired
    private SportService sportService;

    @Autowired
    private SportDataService sportDataService;

    /**
     * 获取排行榜
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultBean getSportList(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<SportListItemVo> sportListItemVos= sportService.getSportList();
        return ResultBeanUtils.successQuery(sportListItemVos);
    }

    /**
     * 天气预报
     * @return
     */
    @RequestMapping(value = "/weather",method = RequestMethod.GET)
    public ResultBean getSportWeather(){
        WeatherVo weatherVo=sportService.getSportWeather();
        return ResultBeanUtils.successQuery(weatherVo);
    }

    /**
     * 大屏人脸注册
     * @param putImageParam
     * @return
     */
    @RequestMapping(value = "/put",method = RequestMethod.POST)
    public ResultBean getSportPutImage(@RequestBody @Validated PutImageParam putImageParam){
        return sportService.getSportPutImage(putImageParam);
    }

    /**
     * 智能摄像头采集数据
     * @param cameraParam
     * @return
     */
    @RequestMapping(value = "/camera",method = RequestMethod.POST)
    public ResultBean getCameraImage(@RequestBody @Validated CameraParam cameraParam){
        ResultBean Image=null;
        if (cameraParam.getType()==1){
            Image= sportService.getCameraImage(cameraParam);
        }
        return Image;
    }

    /**
     * 获取用户信息
     * @param uid
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResultBean getSportGetUser(@RequestParam String uid){
        if(StringUtils.isEmpty(uid)){
            return ResultBeanUtils.error(Codes.ERROR_PARAM,"用户uid不能是空");
        }
        SportDetailsVo detailsVo=sportService.getSportDetails(uid);
        return ResultBeanUtils.successQuery(detailsVo);
    }

    /**
     * 用户信息注册修改
     * @param param
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ResultBean getSportEditUser(@RequestBody EditUserParam param,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        return sportService.editUser(param);
    }

    /**
     * 获取电话验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/get/code",method = RequestMethod.GET)
    public ResultBean getSportGetPhoneCode(@RequestParam String phone, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(StringUtils.isEmpty(phone)){
            return ResultBeanUtils.error(Codes.ERROR_PARAM,"用户电话不能是空");
        }
        return sportService.getPhoneCode(phone);
    }


    /**
     * 获取运动三十天历史记录
     * @param openid
     * @return
     */
    @RequestMapping(value = "/getThirtyDaysHistory",method = RequestMethod.GET)
    public ResultBean getSport(@RequestParam String openid, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(StringUtils.isEmpty(openid)){
            return ResultBeanUtils.error(Codes.ERROR_PARAM,"用户Openid不能是空");
        }
        List<SportData> thirtyDaysHistory = sportDataService.selectThirtyDaysHistory(openid);
        return  ResultBeanUtils.successQuery(thirtyDaysHistory);
    }


    /**
     * 获取运动月\周\日
     * @param openid
     * @return
     */
    @RequestMapping(value = "/getSportInfo",method = RequestMethod.GET)
    public ResultBean getSportInfoByOpenid(@RequestParam String openid, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(StringUtils.isEmpty(openid)){
            return ResultBeanUtils.error(Codes.ERROR_PARAM,"用户Openid不能是空");
        }
        SportWechatDetailsVo sport = sportDataService.getSportDetailsByOpenid(openid);
        return  ResultBeanUtils.successQuery(sport);
    }
}