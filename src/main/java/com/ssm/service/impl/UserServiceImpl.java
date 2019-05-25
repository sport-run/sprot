package com.ssm.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.ssm.commons.bean.ResultBean;
import com.ssm.commons.constants.Codes;
import com.ssm.commons.utils.JsonUtil;
import com.ssm.commons.utils.ResultBeanUtils;
import com.ssm.config.BaiDuProperties;
import com.ssm.enu.SportEnum;
import com.ssm.mapper.UserMapper;
import com.ssm.params.EditUserParam;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.utils.BaiduFaceUtil;
import com.ssm.vo.BaiduAddVo;
import com.ssm.vo.BaiduFaceDetectVo;
import com.ssm.vo.BaiduSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;

/**
 * Created by Administrator on 2017/7/20.
 */
@Component
public class UserServiceImpl implements UserService{
    @Autowired(required = false)
    private UserMapper userMapper;


    @Autowired(required = false)
    private BaiDuProperties baiDuProperties;

    @Override
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultBean insertUser(User user) {
        user.setRegisterTime(new Date());
        User user1=    userMapper.selectUserByOpenid(user.getOpenid());
        if (null!=user1){
            int count =userMapper.updateByOpenidSelective(user);
            if (count>0){
                return ResultBeanUtils.error(Codes.SUCCESS,"更新用户成功!");
            }
        }else{
            int count= userMapper.insertSelective(user);
            if (count>0){
                return ResultBeanUtils.error(Codes.SUCCESS,"注册用户成功!");
            }
        }
        return ResultBeanUtils.error(Codes.UNKNOWN_ERROR,"注册用户失败");
    }

    /**
     * 用户手机号注册
     * @param param
     * @return
     */
    @Override
    public ResultBean updateUser(EditUserParam param) {
        //TODO 校验手机验证
        String detectJson = BaiduFaceUtil.detect(SportEnum.BaiduImageType.BASE64.getType(), param.getImage());
        BaiduFaceDetectVo detectVo = JsonUtil.toObject(BaiduFaceDetectVo.class, detectJson);
        if (detectVo == null || !detectVo.check()) {
            return ResultBeanUtils.error(Codes.UNKNOWN_ERROR,"图像信息不合格");
        }
        String searchJson=BaiduFaceUtil.search(SportEnum.BaiduImageType.BASE64.getType(), param.getImage());
        BaiduSearchVo searchVo=JsonUtil.toObject(BaiduSearchVo.class,searchJson);
        String baiduUid="";
        if (searchVo.getResult().getUser_list().get(0).getScore()>70){
            //判断百度AI哭返回来的数据
            if(searchVo!=null && null!=searchVo.getResult() && searchVo.getResult().getUser_list()!=null){
                if(searchVo.getResult().getUser_list().size()>0){
                    baiduUid=searchVo.getResult().getUser_list().get(0).getUser_id();
                }
            }
        }
        //如果为空需要保存百度AI库
        if(StringUtils.isEmpty(baiduUid)){
             String adduid= numberCode();
            String json=BaiduFaceUtil.add(SportEnum.BaiduImageType.BASE64.getType(), param.getImage(),adduid);
            BaiduAddVo baiduAddVo= JsonUtil.toObject(BaiduAddVo.class,json);
            if(baiduAddVo!=null&&!StringUtils.isEmpty(baiduAddVo.getResult().getFaceToken())){
                    baiduUid=adduid;
            }else {
                return ResultBeanUtils.error(Codes.UNKNOWN_ERROR,"注册用户失败");
            }
        }

        //百度Ai库不为空
        //存在Uid 需要更新
        //不存在Uid 需要新增
        if(!StringUtils.isEmpty(baiduUid)){
            User user=new User();
            user.setWeight(param.getWeight());
            user.setAge(param.getAge());
            user.setHeight(param.getHeight());
            user.setPhone(param.getPhone());
            user.setGender(param.getSex());
            user.setBaiduUid(baiduUid);
            user.setImage(param.getImage());
            user.setUsername(param.getUsername());
            if(StringUtils.isEmpty(param.getUid())) {
//                user.setScore();
                int result=userMapper.insertSelective(user);
                if(result>0){
                    return ResultBeanUtils.successUpdate("注册成功!");
                }else{
                    return ResultBeanUtils.error(Codes.UNKNOWN_ERROR,"注册用户失败");
                }
            }else{
                user.setUid(Integer.parseInt(param.getUid()));
                int result=userMapper.updateByPrimaryKeySelective(user);
                if(result>0){
                    return ResultBeanUtils.successUpdate("修改成功!");
                }else{
                    return ResultBeanUtils.error(Codes.UNKNOWN_ERROR,"修改用户失败");
                }
            }
        }
        return ResultBeanUtils.error(Codes.UNKNOWN_ERROR,"上传头像失败");
    }

    @Override
    public User selectBybaiduUid(String baiduUid) {
        return userMapper.selectBybaiduUid(baiduUid);
    }

    /**
     * 微信注册信息
     * @param user
     * @return
     */
    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    /**
     * 获取用户信息
     * @param penid
     * @return
     */
    @Override
    public ResultBean getUserByOpenid(String penid) {
        User user= userMapper.selectUserByOpenid(penid);
        if (null!=user){
            return ResultBeanUtils.successQuery(user);
        }
        return ResultBeanUtils.error(Codes.UNKNOWN_ERROR,"获取用户失败!");
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
}
