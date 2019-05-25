package com.ssm.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ssm.commons.bean.ResultBean;
import com.ssm.commons.constants.Codes;
import com.ssm.commons.utils.JsonUtil;
import com.ssm.commons.utils.ResultBeanUtils;
import com.ssm.enu.SportEnum;
import com.ssm.params.CameraParam;
import com.ssm.params.EditUserParam;
import com.ssm.params.PutImageParam;
import com.ssm.pojo.*;
import com.ssm.service.*;
import com.ssm.utils.*;
import com.ssm.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SportServiceImpl implements SportService {
    @Autowired
    private SportDataService sportDataService;
    @Autowired
    private UserService userService;
    @Autowired
    private ParkService parkService;
    @Autowired
    private SportDateStatisticsLineService sportDateStatisticsLineService;

    @Override
    public List<SportListItemVo> getSportList() {
        List<SportData> list = sportDataService.getSportWeekList();
        if (CollectionUtil.isNotEmpty(list)) {
            List<SportListItemVo> listItemVos = new ArrayList<>();
            for (SportData sportData : list) {
                SportListItemVo vo = new SportListItemVo();
                vo.setDistance(sportData.getDistance().intValue());
                vo.setUserImage(sportData.getImage());
                listItemVos.add(vo);
            }
            return listItemVos;
        }
        return null;
    }

    @Override
    public WeatherVo getSportWeather() {
        try {
            String weatherJson = HttpUtil.doGet("https", "www.tianqiapi.com/api/", "version=v6");
            WeatherBean weatherBean = JsonUtil.toObject(WeatherBean.class, weatherJson);
            WeatherVo weatherVo = new WeatherVo();
            if (weatherBean != null) {
                weatherVo.setDate(weatherBean.getDate());
                weatherVo.setTemperature(weatherBean.getTem());
                String wea = weatherBean.getWea();
                weatherVo.setWeather(wea);
                weatherVo.setMotionindex("宜跑步");
                if (wea.contains("晴天") || wea.contains("多云")) {
                    weatherVo.setMotionindex("宜跑步");
                }
                if (wea.contains("阴天") || wea.contains("下雨")) {
                    weatherVo.setMotionindex("散步");
                }
                weatherVo.setWeaImg(weatherBean.getWeaImg());
            }

            return weatherVo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultBean getSportPutImage(PutImageParam putImageParam) {
        String detectJson = BaiduFaceUtil.detect(SportEnum.BaiduImageType.BASE64.getType(), putImageParam.getImage());
        BaiduFaceDetectVo detectVo = JsonUtil.toObject(BaiduFaceDetectVo.class, detectJson);
        if (detectVo != null && detectVo.check()) {
            String json = BaiduFaceUtil.search(SportEnum.BaiduImageType.BASE64.getType(), putImageParam.getImage());
            BaiduSearchVo baiduFaceDetectVo = JsonUtil.toObject(BaiduSearchVo.class, json);
            if (baiduFaceDetectVo != null && baiduFaceDetectVo.getResult() != null && baiduFaceDetectVo.getResult().getUser_list().size() > 0) {
                String baiduUid = baiduFaceDetectVo.getResult().getUser_list().get(0).getUser_id();
                System.out.println(baiduFaceDetectVo.getResult().getUser_list().get(0).getScore());
                if (!StringUtils.isEmpty(baiduUid) && baiduFaceDetectVo.getResult().getUser_list().get(0).getScore() > 70) {
                    User user = userService.selectBybaiduUid(baiduUid);
                    return ResultBeanUtils.successQuery(user.getUid());
                } else {
                    return ResultBeanUtils.successQuery(null);
                }
            }
        }

        return ResultBeanUtils.error(Codes.ERROR_PARAM, "用户未注册!");

    }

    @Override
    public SportDetailsVo getSportDetails(String uid) {
        return sportDataService.getSportDetails(uid);
    }

    @Override
    public ResultBean editUser(EditUserParam param) {
        return userService.updateUser(param);
    }

    @Override
    public ResultBean getPhoneCode(String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIVynzBV7hU31j", "O4LDkCO7kLNO3wZfAUkFk193v5UG6X");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "AI智慧公园");
        request.putQueryParameter("TemplateCode", "SMS_165414191");
        String Rand = Message.getFourRandom();
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + Rand + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            return ResultBeanUtils.successQuery(Rand);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return ResultBeanUtils.error(Codes.ERROR_PARAM, "获取验证码失败!");
    }

   /* @Override
    public ResultBean getCameraImage(CameraParam cameraParam) {
        //查询是哪个摄像机返回的数据
        Park park = parkService.queryParkById(1);//默认公园
        if (StringUtils.isEmpty(park.getCameraStart()) || StringUtils.isEmpty(park.getCameraEnd())) {
            return ResultBeanUtils.error(Codes.ERROR_PARAM, "公园摄像机信息确实");
        }

        //起点摄像机
        if (park.getCameraStart().equals(cameraParam.getCid())) {
            //上传百度图像库
            if (CollectionUtil.isNotEmpty(cameraParam.getFaces())) {
                for (String image : cameraParam.getFaces()) {
                    String jsonSearch = BaiduFaceUtil.search(SportEnum.BaiduImageType.BASE64.getType(), image);
                    BaiduSearchVo baiduFaceDetectVo = JsonUtil.toObject(BaiduSearchVo.class, jsonSearch);
                    if (baiduFaceDetectVo != null && baiduFaceDetectVo.getResult() != null && baiduFaceDetectVo.getResult().getUser_list().size() > 0) {
                        String user_id = baiduFaceDetectVo.getResult().getUser_list().get(0).getUser_id();
                        if (StringUtils.isEmpty(user_id)) {
                            String addUid = numberCode();
                            String json = BaiduFaceUtil.add(SportEnum.BaiduImageType.BASE64.getType(), image, addUid);
                            BaiduAddVo addVo = JsonUtil.toObject(BaiduAddVo.class, json);
                            SportDateStatisticsLine statisticsLine = new SportDateStatisticsLine();
                            statisticsLine.setIsStart(1);
                            statisticsLine.setParkId(park.getId());
                            statisticsLine.setImage(image);
                            if (addVo != null) {
                                statisticsLine.setBaiduUid(addUid);
                            }
                            statisticsLine.setTime(new Date(cameraParam.getTime()*1000));
                            int result = sportDateStatisticsLineService.insertSelective(statisticsLine);
                            if (result <= 0) {
                                return ResultBeanUtils.error(Codes.ERROR_PARAM, "统计失败");
                            }
                        } else {
                            SportDateStatisticsLine statisticsLine = sportDateStatisticsLineService.queryByBaiduUid(user_id);
                            if (null!=statisticsLine){
                                statisticsLine.setTime(new Date(cameraParam.getTime()*1000));
                                statisticsLine.setImage(null);//不更新
                                statisticsLine.setStatus(0);//有效
                                int result = sportDateStatisticsLineService.updateSelective(statisticsLine);
                                if (result <= 0) {
                                    return ResultBeanUtils.error(Codes.ERROR_PARAM, "统计失败");
                                }
                            }else {
                                SportDateStatisticsLine  statisticsLine2= new SportDateStatisticsLine();
                                statisticsLine2.setIsStart(1);//是否起点摄像头
                                statisticsLine2.setParkId(park.getId());
                                statisticsLine2.setImage(image);
                                statisticsLine2.setBaiduUid(user_id);
                                statisticsLine2.setTime(new Date(cameraParam.getTime()*1000));
                                int result = sportDateStatisticsLineService.insertSelective(statisticsLine2);
                                if (result <= 0) {
                                    return ResultBeanUtils.error(Codes.ERROR_PARAM, "统计失败");
                                }
                            }
                        }
                    }else {
                        String addUid = numberCode();
                        String json = BaiduFaceUtil.add(SportEnum.BaiduImageType.BASE64.getType(), image, addUid);
                        BaiduAddVo addVo = JsonUtil.toObject(BaiduAddVo.class, json);
                        SportDateStatisticsLine statisticsLine = new SportDateStatisticsLine();
                        statisticsLine.setIsStart(1);
                        statisticsLine.setParkId(park.getId());
                        statisticsLine.setImage(image);
                        if (addVo != null) {
                            statisticsLine.setBaiduUid(addUid);
                        }
                        statisticsLine.setTime(new Date(cameraParam.getTime()*1000));
                        int result = sportDateStatisticsLineService.insertSelective(statisticsLine);
                        if (result <= 0) {
                            return ResultBeanUtils.error(Codes.ERROR_PARAM, "统计失败");
                        }
                    }
                    return ResultBeanUtils.error(Codes.ERROR_PARAM, "统计成功");
                }
            } else {
                return ResultBeanUtils.error(Codes.ERROR_PARAM, "没有人脸图片");
            }
        }
        //终点摄像机
        else if (park.getCameraEnd().equals(cameraParam.getCid())) {
                    //对比百度图像库
                    if (CollectionUtil.isNotEmpty(cameraParam.getFaces())) {
                for (String image : cameraParam.getFaces()) {
                    String json = BaiduFaceUtil.search(SportEnum.BaiduImageType.BASE64.getType(), image);
                    BaiduSearchVo baiduSearchVo = JsonUtil.toObject(BaiduSearchVo.class, json);
                    if (!baiduSearchVo.getError_msg().equals("match user is not found")){
                        if (baiduSearchVo != null && baiduSearchVo.getResult().getUser_list() != null) {
                            if (baiduSearchVo.getResult().getUser_list().size() > 0) {
                                SportDateStatisticsLine startBean = sportDateStatisticsLineService.queryByBaiduUid(baiduSearchVo.getResult().getUser_list().get(0).getUser_id());
                                if (null==startBean)
                                    return ResultBeanUtils.error(Codes.ERROR_PARAM_TYPE, "起点数据未获取");
                                long startTime = startBean.getTime().getTime()/1000;
                                long endTime = cameraParam.getTime();
                                long time=(endTime - startTime)/60;
                                ResultBean bean=saveSportData(park, time, startBean.getBaiduUid());//计算
                                if (bean.getCode()==200){
                                    SportDateStatisticsLine statisticsLine =new SportDateStatisticsLine();
                                    statisticsLine.setId(startBean.getId());
                                    statisticsLine.setStatus(1);//无效起点
                                    sportDateStatisticsLineService.updateSelective(statisticsLine);
                                }
                                return bean;
                            }
                        }
                    }else {
                        return ResultBeanUtils.error(Codes.ERROR_PARAM_TYPE, "仓库未采集");
                    }
                }
            }*//**//*
        } else {
            return ResultBeanUtils.error(Codes.ERROR_PARAM_TYPE, "摄像机id为空");
        }
        return null;
    }*/

    @Override
    public ResultBean getCameraImage(CameraParam cameraParam) {
        //查询是哪个摄像机返回的数据
        Park park = parkService.queryParkById(1);//默认公园
        if (StringUtils.isEmpty(park.getCameraStart()) || StringUtils.isEmpty(park.getCameraEnd())) {
            return ResultBeanUtils.error(Codes.ERROR_PARAM, "公园摄像机信息确实");
        }


        //起点摄像机
        if (park.getCameraStart().equals(cameraParam.getCid())) {
            //上传百度图像库
            if (CollectionUtil.isNotEmpty(cameraParam.getFaces())) {
                for (String image : cameraParam.getFaces()) {
                    if (checkFace(image)) {
                        String jsonSearch = BaiduFaceUtil.search(SportEnum.BaiduImageType.BASE64.getType(), image);
                        BaiduSearchVo baiduFaceDetectVo = JsonUtil.toObject(BaiduSearchVo.class, jsonSearch);
                        if (baiduFaceDetectVo != null && baiduFaceDetectVo.getResult() != null &&
                                baiduFaceDetectVo.getResult().getUser_list() != null &&
                                baiduFaceDetectVo.getResult().getUser_list().size() > 0) {
                            String user_id = baiduFaceDetectVo.getResult().getUser_list().get(0).getUser_id();
                            if (StringUtils.isEmpty(user_id)) {
                                return ResultBeanUtils.error(Codes.ERROR_PARAM, "统计失败");
                            } else {
                                SportDateStatisticsLine statisticsLine = sportDateStatisticsLineService.queryByBaiduUid(user_id);
                                if (null != statisticsLine) {
                                    statisticsLine.setTime(new Date(cameraParam.getTime() * 1000));
                                    statisticsLine.setImage(null);//不更新
                                    statisticsLine.setStatus(0);//有效
                                    int result = sportDateStatisticsLineService.updateSelective(statisticsLine);
                                    if (result <= 0) {
                                        return ResultBeanUtils.error(Codes.ERROR_PARAM, "统计失败");
                                    }
                                } else {
                                    SportDateStatisticsLine statisticsLine2 = new SportDateStatisticsLine();
                                    statisticsLine2.setIsStart(1);//是否起点摄像头
                                    statisticsLine2.setParkId(park.getId());
                                    statisticsLine2.setImage(image);
                                    statisticsLine2.setBaiduUid(user_id);
                                    statisticsLine2.setTime(new Date(cameraParam.getTime() * 1000));
                                    int result = sportDateStatisticsLineService.insertSelective(statisticsLine2);
                                    if (result <= 0) {
                                        return ResultBeanUtils.error(Codes.ERROR_PARAM, "统计失败");
                                    }
                                }
                            }
                        } else {
                            String addUid = numberCode();
                            String json = BaiduFaceUtil.add(SportEnum.BaiduImageType.BASE64.getType(), image, addUid);
                            BaiduAddVo addVo = JsonUtil.toObject(BaiduAddVo.class, json);
                            SportDateStatisticsLine statisticsLine = new SportDateStatisticsLine();
                            statisticsLine.setIsStart(1);//是否起点摄像头  1是 0不是
                            statisticsLine.setParkId(park.getId());
                            statisticsLine.setImage(image);
                            if (addVo != null) {
                                statisticsLine.setBaiduUid(addUid);
                            }
                            statisticsLine.setTime(new Date(cameraParam.getTime() * 1000));
                            int result = sportDateStatisticsLineService.insertSelective(statisticsLine);
                            if (result <= 0) {
                                return ResultBeanUtils.error(Codes.ERROR_PARAM, "统计失败");
                            }
                        }
                        return ResultBeanUtils.error(Codes.ERROR_PARAM, "统计成功");
                    }
                }
            } else {
                return ResultBeanUtils.error(Codes.ERROR_PARAM, "没有人脸图片");
            }
        }
        //终点摄像机
        else if (park.getCameraEnd().equals(cameraParam.getCid())) {
            //对比百度图像库
            if (CollectionUtil.isNotEmpty(cameraParam.getFaces())) {
                for (String image : cameraParam.getFaces()) {
                    if (checkFace(image)) {
                        String json = BaiduFaceUtil.search(SportEnum.BaiduImageType.BASE64.getType(), image);
                        BaiduSearchVo baiduSearchVo = JsonUtil.toObject(BaiduSearchVo.class, json);
                        if (baiduSearchVo != null && null != baiduSearchVo.getResult() && baiduSearchVo.getResult().getUser_list() != null
                                && baiduSearchVo.getResult().getUser_list().size() > 0) {
                            String baiduUid = baiduSearchVo.getResult().getUser_list().get(0).getUser_id();//用户主键
                            SportDateStatisticsLine startBean = sportDateStatisticsLineService.queryByBaiduUid(baiduUid);
                            if (null == startBean)
                                return ResultBeanUtils.error(Codes.ERROR_PARAM_TYPE, "起点数据未获取");
                            long startTime = startBean.getTime().getTime() / 1000;
                            long endTime = cameraParam.getTime();
                            long time = (endTime - startTime) / 60;
                            if (time <= 0) {
                                return ResultBeanUtils.error(Codes.ERROR_PARAM_TYPE, "跑步时间未到一分钟!");
                            }
                            ResultBean bean = saveSportData(park, time, startBean.getBaiduUid());//计算
                            if (bean.getCode() == 200) {
                                SportDateStatisticsLine statisticsLine = new SportDateStatisticsLine();
                                statisticsLine.setId(startBean.getId());
                                statisticsLine.setStatus(1);//设置无效起点
                                sportDateStatisticsLineService.updateSelective(statisticsLine);
                            }
                            return bean;
                        } else {
                            return ResultBeanUtils.error(Codes.ERROR_PARAM_TYPE, "未获取到用户图像信息");
                        }
                    }
                }
            }
        } else {
            return ResultBeanUtils.error(Codes.ERROR_PARAM_TYPE, "摄像机id为空");
        }
        return null;
    }


    private ResultBean saveSportData(Park park, long time, String baiduUid) {
        SportData querySportData = sportDataService.queryByBaiduUid(baiduUid);
        User user = userService.selectBybaiduUid(baiduUid);
        if (querySportData == null) {
            SportData sportData = new SportData();
            sportData.setDate(DateUtil.getBeforeNDays0H0M0S0MS(0));
            sportData.setDistance(new BigDecimal(park.getPerimeter()));
            //设置跑步圈数
            sportData.setCyclenumer(BigDecimal.ONE);
//            消耗热量＝体重*运动时间（时）*指数K，K＝30/速度（分钟/400米），一个	鸡腿的热量=188cal；
//            能量代谢当量(步行)=3.5*0.1*速度（分钟/米）+1.8*速度（分钟/米）*坡度百	分比（如平地可为0），能量代谢当量(步行)=3.5*0.2*速度（分钟/米）+0.9*速度（分钟	/米）*坡度百分比（如平地可为0）；
            //速度单位 m/分钟
            int speed = 0;
            if (time != 0) {
                speed = (int) (park.getPerimeter() / time);
            }
            boolean isRun;
            //速度大于2m/秒
            if (new BigDecimal(speed).divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(2)) > 0) {
                isRun = true;
            } else {
                isRun = false;
            }
            //最大速度
            sportData.setMaxSpeed(new BigDecimal(speed));
            sportData.setSportTime(time);
            sportData.setBaiduUid(baiduUid);
            //代谢当量
            if (isRun) {
                sportData.setEnergyMetabolism(new BigDecimal(3.5 * 0.1 * speed).add(new BigDecimal(1.8 * speed * park.getParkGrade())));
            } else {
                sportData.setEnergyMetabolism(new BigDecimal(3.5 * 0.2 * speed).add(new BigDecimal(0.9 * speed * park.getParkGrade())));
            }
            //消耗热量
            if (user != null) {
                sportData.setEnergyExpenditure(new BigDecimal(user.getWeight() * time / 60f * (30f / speed)));
                sportData.setChickenleg(sportData.getEnergyExpenditure().divide(new BigDecimal(188), 2, BigDecimal.ROUND_HALF_UP));
                sportData.setUid(user.getUid() + "");
            }
            int result = sportDataService.insertSelective(sportData);
            if (result > 0) {
                return ResultBeanUtils.successUpdate();
            }
        } else {
            //
            querySportData.setDistance(new BigDecimal(park.getPerimeter()).add(querySportData.getDistance()));
//            消耗热量＝体重*运动时间（时）*指数K，K＝30/速度（分钟/400米），一个	鸡腿的热量=188cal；
//            能量代谢当量(步行)=3.5*0.1*速度（分钟/米）+1.8*速度（分钟/米）*坡度百	分比（如平地可为0），能量代谢当量(步行)=3.5*0.2*速度（分钟/米）+0.9*速度（分钟	/米）*坡度百分比（如平地可为0）；
            int speed = (int) (park.getPerimeter() / time);
            boolean isRun;
            //速度大于2m/秒
            if (new BigDecimal(speed).divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(2)) > 0) {
                isRun = true;
            } else {
                isRun = false;
            }
            if (querySportData.getMaxSpeed().intValue() < speed) {
                querySportData.setMaxSpeed(new BigDecimal(speed));
            }
            querySportData.setSportTime(time + querySportData.getSportTime());
            querySportData.setCyclenumer(querySportData.getCyclenumer().add(BigDecimal.ONE));
            //代谢当量
            BigDecimal energyMetabolism;
            if (isRun) {
                energyMetabolism = new BigDecimal(3.5 * 0.1 * speed).add(new BigDecimal(1.8 * speed * park.getParkGrade()));
            } else {
                energyMetabolism = new BigDecimal(3.5 * 0.2 * speed).add(new BigDecimal(0.9 * speed * park.getParkGrade()));
            }
            querySportData.setEnergyMetabolism(querySportData.getEnergyMetabolism().add(energyMetabolism));

            if (user != null) {
                BigDecimal energyExpenditure = new BigDecimal(user.getWeight() * time / 60f * (30f / speed));
                querySportData.setEnergyExpenditure(querySportData.getEnergyExpenditure().add(energyExpenditure));
                BigDecimal chickenleg = querySportData.getEnergyExpenditure().divide(new BigDecimal(188), 2, BigDecimal.ROUND_HALF_UP);
                querySportData.setChickenleg(querySportData.getChickenleg().add(chickenleg));
                querySportData.setUid(user.getUid() + "");
            }
            int result = sportDataService.updateByPrimaryKeySelective(querySportData);
            if (result > 0) {
                return ResultBeanUtils.successUpdate();
            }
        }
        return ResultBeanUtils.error(Codes.ERROR_PARAM_TYPE, "计算失败");
    }

    /**
     * 简单数字码，碰撞率较高
     *
     * @return
     */
    public String numberCode() {
        Random random = new Random();
        Formatter formatter = new Formatter();
        formatter.format("%06d", random.nextInt(1000000));
        String code = String.valueOf(Math.abs(Calendar.getInstance().hashCode())) + formatter;//时间hash+4位随机
        return code;
    }

    private boolean checkFace(String image) {
        String detectJson = BaiduFaceUtil.detect(SportEnum.BaiduImageType.BASE64.getType(), image);
        BaiduFaceDetectVo detectVo = JsonUtil.toObject(BaiduFaceDetectVo.class, detectJson);
        if (detectVo != null && detectVo.check()) {
            return true;
        }
        return false;
    }
}
