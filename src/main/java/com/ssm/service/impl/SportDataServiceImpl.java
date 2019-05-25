package com.ssm.service.impl;

import com.ssm.mapper.SportDataMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.SportData;
import com.ssm.pojo.User;
import com.ssm.service.SportDataService;
import com.ssm.utils.CollectionUtil;
import com.ssm.utils.DateUtil;
import com.ssm.vo.SportDetailItemVo;
import com.ssm.vo.SportDetailsVo;
import com.ssm.vo.SportWechatDetailsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SportDataServiceImpl implements SportDataService {
    @Resource
    private SportDataMapper sportDataMapper;
    /**
     * 获取用户头像
     */
    @Resource
    private UserMapper userMapper;

   /* @Override
    public SportDetailsVo getSportDetails(String uid) {
        Date startTodayDate = DateUtil.getBeforeNDays0H0M0S0MS(0);
        Date endTodayDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        Date startWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(6);
        Date endWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        Date startMDate = DateUtil.getBeforeNDays0H0M0S0MS(29);
        Date endMDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        List<SportData> sportTodayDataList = sportDataMapper.queryAllByUid(uid, startTodayDate, endTodayDate);
        List<SportData> sportWeekDataList = sportDataMapper.queryAllByUid(uid, startWeekDate, endWeekDate);
        List<SportData> sportMDataList = sportDataMapper.queryAllByUid(uid, startMDate, endMDate);
        if (CollectionUtil.isNotEmpty(sportMDataList)) {
            SportDetailsVo vo = new SportDetailsVo();
            BigDecimal todayDistance = BigDecimal.ZERO;
            BigDecimal todayExpenditure = BigDecimal.ZERO;
            BigDecimal todayMetabolism = BigDecimal.ZERO;
            long todayTime=0;
            SportDetailItemVo todayItem=new SportDetailItemVo();
            for (SportData sportData : sportTodayDataList) {
                todayMetabolism=todayDistance.add(sportData.getDistance());
                todayExpenditure=todayExpenditure.add(sportData.getEnergyExpenditure());
                todayMetabolism=todayMetabolism.add(sportData.getEnergyMetabolism());
                todayTime = (todayTime + sportData.getSportTime())/(60*60);
            }
            todayItem.setDistance(todayDistance);
            todayItem.setEnergyExpenditure(todayExpenditure);
            todayItem.setEnergyMetabolism(todayMetabolism);
            todayItem.setTime(todayTime);
            if(todayTime>0) {
                todayItem.setSpeed(todayDistance.divide(new BigDecimal(todayTime), 8, BigDecimal.ROUND_DOWN));
            }else {
                todayItem.setSpeed(new BigDecimal(0));
            }

            BigDecimal weekDistance = BigDecimal.ZERO;
            BigDecimal weekExpenditure = BigDecimal.ZERO;
            BigDecimal weekMetabolism = BigDecimal.ZERO;
            long weekTime=0;
            SportDetailItemVo weekItem=new SportDetailItemVo();
            int runCount=0;
            List<Map<String,String>> weekDate=new ArrayList<>();
            for (SportData sportData : sportWeekDataList) {
                HashMap<String,String> weekItemDate=new HashMap<>();
                weekItemDate.put(DateUtil.format(sportData.getDate(),DateUtil.yyyyMMdd),sportData.getDistance().toPlainString());
                weekDate.add(weekItemDate);
                weekDistance=weekDistance.add(sportData.getDistance());
                weekExpenditure=weekExpenditure.add(sportData.getEnergyExpenditure());
                weekMetabolism=weekMetabolism.add(sportData.getEnergyMetabolism());
                weekTime = (weekTime + sportData.getSportTime())/(60*60);
                if(sportData.getSportTime()>0) {
                    if (sportData.getDistance().divide(new BigDecimal(sportData.getSportTime()),0,BigDecimal.ROUND_DOWN).compareTo(BigDecimal.ONE) > 0) {
                        runCount += 1;
                    }
                }
            }
            weekItem.setDistance(weekDistance);
            weekItem.setEnergyExpenditure(weekExpenditure);
            weekItem.setEnergyMetabolism(weekMetabolism);
            weekItem.setTime(weekTime);
            if(weekTime>0) {
                weekItem.setSpeed(weekDistance.divide(new BigDecimal(weekTime), 0, BigDecimal.ROUND_DOWN));
            }else {
                weekItem.setSpeed(new BigDecimal(0));
            }
            BigDecimal mothDistance = BigDecimal.ZERO;
            BigDecimal mothExpenditure = BigDecimal.ZERO;
            BigDecimal mothMetabolism = BigDecimal.ZERO;
            long mothTime=0;
            SportDetailItemVo mothItem=new SportDetailItemVo();
            List<Map<String,String>> mothDate=new ArrayList<>();
            for (SportData sportData : sportMDataList) {
                HashMap<String,String> mothItemDate=new HashMap<>();
                mothItemDate.put(DateUtil.format(sportData.getDate(),DateUtil.yyyyMMdd),sportData.getDistance().toPlainString());
                mothDate.add(mothItemDate);
                mothDistance=mothDistance.add(sportData.getDistance());
                mothExpenditure=mothExpenditure.add(sportData.getEnergyExpenditure());
                mothMetabolism=mothMetabolism.add(sportData.getEnergyMetabolism());
                mothTime = (mothTime + sportData.getSportTime())/(60*60);
                System.out.print(mothTime);
            }
            mothItem.setDistance(mothDistance);
            mothItem.setEnergyExpenditure(mothExpenditure);
            mothItem.setEnergyMetabolism(mothMetabolism);
            mothItem.setTime(mothTime);
            if(mothTime>0) {
                mothItem.setSpeed(mothDistance.divide(new BigDecimal(mothTime), 0, BigDecimal.ROUND_DOWN));
            }else {
                weekItem.setSpeed(new BigDecimal(0));
            }
            String rate;
            if(sportWeekDataList.size()>=5){
                rate="频繁";
            }else if(sportWeekDataList.size()>=2){
                rate="中";
            }else{
                rate="低";
            }
            todayItem.setRate(rate);
            todayItem.setHabits(runCount>=4?"跑步":"散步");
            vo.setTodayItem(todayItem);
            vo.setWeekItem(weekItem);
            vo.setMothItem(mothItem);
            vo.setWeek(weekDate);
            vo.setMoth(mothDate);
            return vo;
        }
        return null;
    }*/

    /**
     * 大屏
     * @param uid
     * @return
     */
    public SportDetailsVo getSportDetails(String uid) {
        Date startTodayDate = DateUtil.getBeforeNDays0H0M0S0MS(0);
        Date endTodayDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        Date startWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(6);
        Date endWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        Date startMDate = DateUtil.getBeforeNDays0H0M0S0MS(29);
        Date endMDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        List<SportData> sportTodayDataList = sportDataMapper.queryAllByUid(uid, startTodayDate, endTodayDate);
        List<SportData> sportWeekDataList = sportDataMapper.queryAllByUid(uid, startWeekDate, endWeekDate);
        List<SportData> sportMDataList = sportDataMapper.queryAllByUid(uid, startMDate, endMDate);
        if (CollectionUtil.isNotEmpty(sportMDataList)) {
            SportDetailsVo vo = new SportDetailsVo();
            BigDecimal todayDistance = BigDecimal.ZERO;
            BigDecimal todayExpenditure = BigDecimal.ZERO;
            BigDecimal todayMetabolism = BigDecimal.ZERO;
            long todayTime = 0;
            SportDetailItemVo todayItem = new SportDetailItemVo();
            for (SportData sportData : sportTodayDataList) {
                todayMetabolism = todayDistance.add(sportData.getDistance());
                todayExpenditure = todayExpenditure.add(sportData.getEnergyExpenditure());
                todayMetabolism = todayMetabolism.add(sportData.getEnergyMetabolism());
                todayTime = (todayTime + sportData.getSportTime());
            }
            todayItem.setDistance(todayDistance);
            todayItem.setEnergyExpenditure(todayExpenditure);
            todayItem.setEnergyMetabolism(todayMetabolism);
            todayItem.setTime(todayTime);
            if (todayTime > 0) {
                todayItem.setSpeed(todayDistance.divide(new BigDecimal(todayTime), 2, BigDecimal.ROUND_DOWN));
            }else {
                todayItem.setSpeed(new BigDecimal(0));
            }

            BigDecimal weekDistance = BigDecimal.ZERO;
            BigDecimal weekExpenditure = BigDecimal.ZERO;
            BigDecimal weekMetabolism = BigDecimal.ZERO;
            long weekTime = 0;
            SportDetailItemVo weekItem = new SportDetailItemVo();
            int runCount = 0;

            List<Map<String, ArrayList<BigDecimal>>> weekDate = new ArrayList<>();
            ArrayList<BigDecimal> weekDistanceList = new ArrayList<>();
            ArrayList<BigDecimal> weekTimeList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                weekDistanceList.add(i,BigDecimal.ZERO);
                weekTimeList.add(i,BigDecimal.ZERO);
            }
            for (SportData sportData : sportWeekDataList) {
                int index = dateDiff(DateUtil.format(startWeekDate,"yyyy-MM-dd"),DateUtil.format(sportData.getDate(),
                        "yyyy-MM-dd"),"yyyy-MM-dd");
                weekDistanceList.remove(index);
                weekDistanceList.add(index,sportData.getDistance());
                weekTimeList.remove(index);
                weekTimeList.add(index,new BigDecimal(sportData.getSportTime()));
                weekDistance = weekDistance.add(sportData.getDistance());
                weekExpenditure = weekExpenditure.add(sportData.getEnergyExpenditure());
                weekMetabolism = weekMetabolism.add(sportData.getEnergyMetabolism());
                weekTime = (weekTime + sportData.getSportTime());
                if (sportData.getSportTime() > 0) {
                    if (sportData.getDistance().divide(new BigDecimal(sportData.getSportTime()), 0, BigDecimal.ROUND_DOWN).compareTo(BigDecimal.ONE) > 0) {
                        runCount += 1;
                    }
                }
            }
            HashMap<String, ArrayList<BigDecimal>> weekItemDate = new HashMap<>();
            weekItemDate.put("time", weekTimeList);
            weekItemDate.put("distance", weekDistanceList);
            weekDate.add(weekItemDate);

            weekItem.setDistance(weekDistance);
            weekItem.setEnergyExpenditure(weekExpenditure);
            weekItem.setEnergyMetabolism(weekMetabolism);
            weekItem.setTime(weekTime);
            if (weekTime > 0) {
                weekItem.setSpeed(weekDistance.divide(new BigDecimal(weekTime), 0, BigDecimal.ROUND_DOWN));
            }else {
                weekItem.setSpeed(new BigDecimal(0));
            }
            BigDecimal mothDistance = BigDecimal.ZERO;
            BigDecimal mothExpenditure = BigDecimal.ZERO;
            BigDecimal mothMetabolism = BigDecimal.ZERO;
            long mothTime = 0;
            SportDetailItemVo mothItem = new SportDetailItemVo();

            List<Map<String, ArrayList<BigDecimal>>> mothDate = new ArrayList<>();
            ArrayList<BigDecimal> mothDistanceList = new ArrayList<>();
            ArrayList<BigDecimal> mothTimeList = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                mothDistanceList.add(i,BigDecimal.ZERO);
                mothTimeList.add(i,BigDecimal.ZERO);
            }
            for (SportData sportData : sportMDataList) {
                int index = dateDiff(DateUtil.format(startMDate,"yyyy-MM-dd"),DateUtil.format(sportData.getDate(),
                        "yyyy-MM-dd"),"yyyy-MM-dd");
                mothDistanceList.remove(index);
                mothDistanceList.add(index,sportData.getDistance());
                mothTimeList.remove(index);
                mothTimeList.add(index,new BigDecimal(sportData.getSportTime()));
                mothDistance = mothDistance.add(sportData.getDistance());
                mothExpenditure = mothExpenditure.add(sportData.getEnergyExpenditure());
                mothMetabolism = mothMetabolism.add(sportData.getEnergyMetabolism());
                mothTime = (mothTime + sportData.getSportTime());
                System.out.print(mothTime);
            }
            HashMap<String, ArrayList<BigDecimal>> mothItemDate = new HashMap<>();
            mothItemDate.put("time", mothTimeList);
            mothItemDate.put("distance", mothDistanceList);
            mothDate.add(mothItemDate);


            mothItem.setDistance(mothDistance);
            mothItem.setEnergyExpenditure(mothExpenditure);
            mothItem.setEnergyMetabolism(mothMetabolism);
            mothItem.setTime(mothTime);
            if (mothTime > 0) {
                mothItem.setSpeed(mothDistance.divide(new BigDecimal(mothTime), 0, BigDecimal.ROUND_DOWN));
            }else {
                mothItem.setSpeed(new BigDecimal(0));
            }
            String rate;
            if (sportWeekDataList.size() >= 5) {
                rate = "频繁";
            } else if (sportWeekDataList.size() >= 2) {
                rate = "中";
            } else {
                rate = "低";
            }
            todayItem.setRate(rate);
            todayItem.setHabits(runCount >= 4 ? "跑步" : "散步");
            vo.setTodayItem(todayItem);
            vo.setWeekItem(weekItem);
            vo.setMothItem(mothItem);
            vo.setWeek(weekDate);
            vo.setMoth(mothDate);
            return vo;
        }
        return null;
    }

    /**
     * 微信openid
     * @param openid
     * @return
     */
    public SportWechatDetailsVo getSportDetailsByOpenid(String openid) {
        Date startTodayDate = DateUtil.getBeforeNDays0H0M0S0MS(0);
        Date endTodayDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        Date startWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(6);
        Date endWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        Date startMDate = DateUtil.getBeforeNDays0H0M0S0MS(29);
        Date endMDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        List<SportData> sportTodayDataList = sportDataMapper.queryWechatAllByOpenid(openid, startTodayDate, endTodayDate);
        List<SportData> sportWeekDataList = sportDataMapper.queryWechatAllByOpenid(openid, startWeekDate, endWeekDate);
        List<SportData> sportMDataList = sportDataMapper.queryWechatAllByOpenid(openid, startMDate, endMDate);
        if (CollectionUtil.isNotEmpty(sportMDataList)) {
            SportWechatDetailsVo vo = new SportWechatDetailsVo();
            BigDecimal todayDistance = BigDecimal.ZERO;
            BigDecimal todayExpenditure = BigDecimal.ZERO;
            BigDecimal todayMetabolism = BigDecimal.ZERO;
            long todayTime = 0;
            SportDetailItemVo todayItem = new SportDetailItemVo();
            for (SportData sportData : sportTodayDataList) {
                todayMetabolism = todayDistance.add(sportData.getDistance());
                todayExpenditure = todayExpenditure.add(sportData.getEnergyExpenditure());
                todayMetabolism = todayMetabolism.add(sportData.getEnergyMetabolism());
                todayTime = (todayTime + sportData.getSportTime());
            }
            todayItem.setDistance(todayDistance);
            todayItem.setEnergyExpenditure(todayExpenditure);
            todayItem.setEnergyMetabolism(todayMetabolism);
            todayItem.setTime(todayTime);
            if (todayTime > 0) {
                todayItem.setSpeed(todayDistance.divide(new BigDecimal(todayTime), 2, BigDecimal.ROUND_DOWN));
            }else {
                todayItem.setSpeed(new BigDecimal(0));
            }

            BigDecimal weekDistance = BigDecimal.ZERO;
            BigDecimal weekExpenditure = BigDecimal.ZERO;
            BigDecimal weekMetabolism = BigDecimal.ZERO;
            long weekTime = 0;
            SportDetailItemVo weekItem = new SportDetailItemVo();
            int runCount = 0;

         /*   List<Map<String, ArrayList<BigDecimal>>> weekDate = new ArrayList<>();
            ArrayList<BigDecimal> weekDistanceList = new ArrayList<>();
            ArrayList<BigDecimal> weekTimeList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                weekDistanceList.add(i,BigDecimal.ZERO);
                weekTimeList.add(i,BigDecimal.ZERO);
            }
            for (SportData sportData : sportWeekDataList) {
                int index = dateDiff(DateUtil.format(startWeekDate,"yyyy-MM-dd"),DateUtil.format(sportData.getDate(),
                        "yyyy-MM-dd"),"yyyy-MM-dd");
                weekDistanceList.remove(index);
                weekDistanceList.add(index,sportData.getDistance());
                weekTimeList.remove(index);
                weekTimeList.add(index,new BigDecimal(sportData.getSportTime()));
                weekDistance = weekDistance.add(sportData.getDistance());
                weekExpenditure = weekExpenditure.add(sportData.getEnergyExpenditure());
                weekMetabolism = weekMetabolism.add(sportData.getEnergyMetabolism());
                weekTime = (weekTime + sportData.getSportTime());
                if (sportData.getSportTime() > 0) {
                    if (sportData.getDistance().divide(new BigDecimal(sportData.getSportTime()), 0, BigDecimal.ROUND_DOWN).compareTo(BigDecimal.ONE) > 0) {
                        runCount += 1;
                    }
                }
            }
            HashMap<String, ArrayList<BigDecimal>> weekItemDate = new HashMap<>();
            weekItemDate.put("time", weekTimeList);
            weekItemDate.put("distance", weekDistanceList);
            weekDate.add(weekItemDate);*/

            weekItem.setDistance(weekDistance);
            weekItem.setEnergyExpenditure(weekExpenditure);
            weekItem.setEnergyMetabolism(weekMetabolism);
            weekItem.setTime(weekTime);
            if (weekTime > 0) {
                weekItem.setSpeed(weekDistance.divide(new BigDecimal(weekTime), 0, BigDecimal.ROUND_DOWN));
            }else {
                weekItem.setSpeed(new BigDecimal(0));
            }
            BigDecimal mothDistance = BigDecimal.ZERO;
            BigDecimal mothExpenditure = BigDecimal.ZERO;
            BigDecimal mothMetabolism = BigDecimal.ZERO;
            long mothTime = 0;
            SportDetailItemVo mothItem = new SportDetailItemVo();

/*            List<Map<String, ArrayList<BigDecimal>>> mothDate = new ArrayList<>();
            ArrayList<BigDecimal> mothDistanceList = new ArrayList<>();
            ArrayList<BigDecimal> mothTimeList = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                mothDistanceList.add(i,BigDecimal.ZERO);
                mothTimeList.add(i,BigDecimal.ZERO);
            }
            for (SportData sportData : sportMDataList) {
                int index = dateDiff(DateUtil.format(startMDate,"yyyy-MM-dd"),DateUtil.format(sportData.getDate(),
                        "yyyy-MM-dd"),"yyyy-MM-dd");
                mothDistanceList.remove(index);
                mothDistanceList.add(index,sportData.getDistance());
                mothTimeList.remove(index);
                mothTimeList.add(index,new BigDecimal(sportData.getSportTime()));
                mothDistance = mothDistance.add(sportData.getDistance());
                mothExpenditure = mothExpenditure.add(sportData.getEnergyExpenditure());
                mothMetabolism = mothMetabolism.add(sportData.getEnergyMetabolism());
                mothTime = (mothTime + sportData.getSportTime());
                System.out.print(mothTime);
            }
            HashMap<String, ArrayList<BigDecimal>> mothItemDate = new HashMap<>();
            mothItemDate.put("time", mothTimeList);
            mothItemDate.put("distance", mothDistanceList);
            mothDate.add(mothItemDate);*/


            mothItem.setDistance(mothDistance);
            mothItem.setEnergyExpenditure(mothExpenditure);
            mothItem.setEnergyMetabolism(mothMetabolism);
            mothItem.setTime(mothTime);
            if (mothTime > 0) {
                mothItem.setSpeed(mothDistance.divide(new BigDecimal(mothTime), 0, BigDecimal.ROUND_DOWN));
            }else {
                mothItem.setSpeed(new BigDecimal(0));
            }
            String rate;
            if (sportWeekDataList.size() >= 5) {
                rate = "频繁";
            } else if (sportWeekDataList.size() >= 2) {
                rate = "中";
            } else {
                rate = "低";
            }
            todayItem.setRate(rate);
            todayItem.setHabits(runCount >= 4 ? "跑步" : "散步");
            vo.setTodayItem(todayItem);
            vo.setWeekItem(weekItem);
            vo.setMothItem(mothItem);
          /*  vo.setWeek(weekDate);*/
           /* vo.setMoth(mothDate);*/
            return vo;
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SportData sportData) {
        return sportDataMapper.updateByPrimaryKeySelective(sportData);
    }
    /*public SportDetailsVo getSportDetails(String uid) {
        Date startTodayDate = DateUtil.getBeforeNDays0H0M0S0MS(0);
        Date endTodayDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        Date startWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(6);
        Date endWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        Date startMDate = DateUtil.getBeforeNDays0H0M0S0MS(29);
        Date endMDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        List<SportData> sportTodayDataList = sportDataMapper.queryAllByUid(uid, startTodayDate, endTodayDate);
        List<SportData> sportWeekDataList = sportDataMapper.queryAllByUid(uid, startWeekDate, endWeekDate);
        List<SportData> sportMDataList = sportDataMapper.queryAllByUid(uid, startMDate, endMDate);
        if (CollectionUtil.isNotEmpty(sportMDataList)) {
            SportDetailsVo vo = new SportDetailsVo();
            BigDecimal todayDistance = BigDecimal.ZERO;
            BigDecimal todayExpenditure = BigDecimal.ZERO;
            BigDecimal todayMetabolism = BigDecimal.ZERO;
            long todayTime = 0;
            SportDetailItemVo todayItem = new SportDetailItemVo();
            for (SportData sportData : sportTodayDataList) {
                todayMetabolism = todayDistance.add(sportData.getDistance());
                todayExpenditure = todayExpenditure.add(sportData.getEnergyExpenditure());
                todayMetabolism = todayMetabolism.add(sportData.getEnergyMetabolism());
                todayTime = (todayTime + sportData.getSportTime());
            }
            todayItem.setDistance(todayDistance);
            todayItem.setEnergyExpenditure(todayExpenditure);
            todayItem.setEnergyMetabolism(todayMetabolism);
            todayItem.setTime(todayTime);
            if (todayTime > 0) {
                todayItem.setSpeed(todayDistance.divide(new BigDecimal(todayTime), 2, BigDecimal.ROUND_DOWN));
            }else {
                todayItem.setSpeed(new BigDecimal(0));
            }

            BigDecimal weekDistance = BigDecimal.ZERO;
            BigDecimal weekExpenditure = BigDecimal.ZERO;
            BigDecimal weekMetabolism = BigDecimal.ZERO;
            long weekTime = 0;
            SportDetailItemVo weekItem = new SportDetailItemVo();
            int runCount = 0;

            List<Map<String, ArrayList<BigDecimal>>> weekDate = new ArrayList<>();
            ArrayList<BigDecimal> weekDistanceList = new ArrayList<>();
            ArrayList<BigDecimal> weekTimeList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                weekDistanceList.add(i,BigDecimal.ZERO);
                weekTimeList.add(i,BigDecimal.ZERO);
            }
            for (SportData sportData : sportWeekDataList) {
                int index = dateDiff(DateUtil.format(startWeekDate,"yyyy-MM-dd"),DateUtil.format(sportData.getDate(),
                        "yyyy-MM-dd"),"yyyy-MM-dd");
                weekDistanceList.add(index,sportData.getDistance());
                weekTimeList.add(index,new BigDecimal(sportData.getSportTime()));
                weekDistance = weekDistance.add(sportData.getDistance());
                weekExpenditure = weekExpenditure.add(sportData.getEnergyExpenditure());
                weekMetabolism = weekMetabolism.add(sportData.getEnergyMetabolism());
                weekTime = (weekTime + sportData.getSportTime());
                if (sportData.getSportTime() > 0) {
                    if (sportData.getDistance().divide(new BigDecimal(sportData.getSportTime()), 0, BigDecimal.ROUND_DOWN).compareTo(BigDecimal.ONE) > 0) {
                        runCount += 1;
                    }
                }
            }
            HashMap<String, ArrayList<BigDecimal>> weekItemDate = new HashMap<>();
            weekItemDate.put("time", weekTimeList);
            weekItemDate.put("distance", weekDistanceList);
            weekDate.add(weekItemDate);

            weekItem.setDistance(weekDistance);
            weekItem.setEnergyExpenditure(weekExpenditure);
            weekItem.setEnergyMetabolism(weekMetabolism);
            weekItem.setTime(weekTime);
            if (weekTime > 0) {
                weekItem.setSpeed(weekDistance.divide(new BigDecimal(weekTime), 0, BigDecimal.ROUND_DOWN));
            }else {
                weekItem.setSpeed(new BigDecimal(0));
            }
            BigDecimal mothDistance = BigDecimal.ZERO;
            BigDecimal mothExpenditure = BigDecimal.ZERO;
            BigDecimal mothMetabolism = BigDecimal.ZERO;
            long mothTime = 0;
            SportDetailItemVo mothItem = new SportDetailItemVo();

            List<Map<String, ArrayList<BigDecimal>>> mothDate = new ArrayList<>();
            ArrayList<BigDecimal> mothDistanceList = new ArrayList<>();
            ArrayList<BigDecimal> mothTimeList = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                mothDistanceList.add(i,BigDecimal.ZERO);
                mothTimeList.add(i,BigDecimal.ZERO);
            }
            for (SportData sportData : sportMDataList) {
                int index = dateDiff(DateUtil.format(startWeekDate,"yyyy-MM-dd"),DateUtil.format(sportData.getDate(),
                        "yyyy-MM-dd"),"yyyy-MM-dd");
                mothDistanceList.add(index,sportData.getDistance());
                mothTimeList.add(index,new BigDecimal(sportData.getSportTime()));
                mothDistance = mothDistance.add(sportData.getDistance());
                mothExpenditure = mothExpenditure.add(sportData.getEnergyExpenditure());
                mothMetabolism = mothMetabolism.add(sportData.getEnergyMetabolism());
                mothTime = (mothTime + sportData.getSportTime());
                System.out.print(mothTime);
            }
            HashMap<String, ArrayList<BigDecimal>> mothItemDate = new HashMap<>();
            mothItemDate.put("time", mothTimeList);
            mothItemDate.put("distance", mothDistanceList);
            mothDate.add(mothItemDate);


            mothItem.setDistance(mothDistance);
            mothItem.setEnergyExpenditure(mothExpenditure);
            mothItem.setEnergyMetabolism(mothMetabolism);
            mothItem.setTime(mothTime);
            if (mothTime > 0) {
                mothItem.setSpeed(mothDistance.divide(new BigDecimal(mothTime), 0, BigDecimal.ROUND_DOWN));
            }else {
                mothItem.setSpeed(new BigDecimal(0));
            }
            String rate;
            if (sportWeekDataList.size() >= 5) {
                rate = "频繁";
            } else if (sportWeekDataList.size() >= 2) {
                rate = "中";
            } else {
                rate = "低";
            }
            todayItem.setRate(rate);
            todayItem.setHabits(runCount >= 4 ? "跑步" : "散步");
            vo.setTodayItem(todayItem);
            vo.setWeekItem(weekItem);
            vo.setMothItem(mothItem);
            vo.setWeek(weekDate);
            vo.setMoth(mothDate);
            return vo;
        }
        return null;
    }*/
   /*@Override
   public SportDetailsVo getSportDetails(String uid) {
       Date startTodayDate = DateUtil.getBeforeNDays0H0M0S0MS(0);
       Date endTodayDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
       Date startWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(6);
       Date endWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
       Date startMDate = DateUtil.getBeforeNDays0H0M0S0MS(29);
       Date endMDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
       User user= userMapper.selectByPrimaryKey(Integer.parseInt(uid));
       List<SportData> sportTodayDataList = sportDataMapper.queryAllByUid(uid, startTodayDate, endTodayDate);
       List<SportData> sportWeekDataList = sportDataMapper.queryAllByUid(uid, startWeekDate, endWeekDate);
       List<SportData> sportMDataList = sportDataMapper.queryAllByUid(uid, startMDate, endMDate);
       if (CollectionUtil.isNotEmpty(sportMDataList)) {
           SportDetailsVo vo = new SportDetailsVo();
           BigDecimal todayDistance = BigDecimal.ZERO;
           BigDecimal todayExpenditure = BigDecimal.ZERO;
           BigDecimal todayMetabolism = BigDecimal.ZERO;
           long todayTime=0;
           SportDetailItemVo todayItem=new SportDetailItemVo();
           for (SportData sportData : sportTodayDataList) {
               todayMetabolism=todayDistance.add(sportData.getDistance());
               todayExpenditure=todayExpenditure.add(sportData.getEnergyExpenditure());
               todayMetabolism=todayMetabolism.add(sportData.getEnergyMetabolism());
               todayTime = (todayTime + sportData.getSportTime());
           }
           todayItem.setDistance(todayDistance);
           todayItem.setEnergyExpenditure(todayExpenditure);
           todayItem.setEnergyMetabolism(todayMetabolism);
           todayItem.setTime(todayTime);
           if(todayTime>0) {
               todayItem.setSpeed(todayDistance.divide(new BigDecimal(todayTime), 2, BigDecimal.ROUND_DOWN));
           }else {
               todayItem.setSpeed(new BigDecimal(0));
           }

           BigDecimal weekDistance = BigDecimal.ZERO;
           BigDecimal weekExpenditure = BigDecimal.ZERO;
           BigDecimal weekMetabolism = BigDecimal.ZERO;
           long weekTime=0;
           SportDetailItemVo weekItem=new SportDetailItemVo();
           int runCount=0;

           List<Map<String,ArrayList<BigDecimal>>> weekDate=new ArrayList<>();
           ArrayList<BigDecimal> weekDistanceList=new ArrayList<>();
           ArrayList<BigDecimal> weekTimeList=new ArrayList<>();
           for (SportData sportData : sportWeekDataList) {
               weekDistanceList.add(sportData.getDistance());
               weekTimeList.add(new BigDecimal(sportData.getSportTime()));
               weekDistance=weekDistance.add(sportData.getDistance());
               weekExpenditure=weekExpenditure.add(sportData.getEnergyExpenditure());
               weekMetabolism=weekMetabolism.add(sportData.getEnergyMetabolism());
               weekTime = (weekTime + sportData.getSportTime());
               if(sportData.getSportTime()>0) {
                   if (sportData.getDistance().divide(new BigDecimal(sportData.getSportTime()),0,BigDecimal.ROUND_DOWN).compareTo(BigDecimal.ONE) > 0) {
                       runCount += 1;
                   }
               }
           }
           HashMap<String,ArrayList<BigDecimal>> weekItemDate=new HashMap<>();
           weekItemDate.put("time",weekTimeList);
           weekItemDate.put("distance",weekDistanceList);
           weekDate.add(weekItemDate);

           weekItem.setDistance(weekDistance);
           weekItem.setEnergyExpenditure(weekExpenditure);
           weekItem.setEnergyMetabolism(weekMetabolism);
           weekItem.setTime(weekTime);
           if(weekTime>0) {
               weekItem.setSpeed(weekDistance.divide(new BigDecimal(weekTime), 0, BigDecimal.ROUND_DOWN));
           }else {
               weekItem.setSpeed(new BigDecimal(0));
           }
           BigDecimal mothDistance = BigDecimal.ZERO;
           BigDecimal mothExpenditure = BigDecimal.ZERO;
           BigDecimal mothMetabolism = BigDecimal.ZERO;
           long mothTime=0;
           SportDetailItemVo mothItem=new SportDetailItemVo();

           List<Map<String,ArrayList<BigDecimal>>> mothDate=new ArrayList<>();
           ArrayList<BigDecimal> mothDistanceList=new ArrayList<>();
           ArrayList<BigDecimal> mothTimeList=new ArrayList<>();
           for (SportData sportData : sportMDataList) {
               mothDistanceList.add(sportData.getDistance());
               mothTimeList.add(new BigDecimal(sportData.getSportTime()));
               mothDistance=mothDistance.add(sportData.getDistance());
               mothExpenditure=mothExpenditure.add(sportData.getEnergyExpenditure());
               mothMetabolism=mothMetabolism.add(sportData.getEnergyMetabolism());
               mothTime = (mothTime + sportData.getSportTime());
               System.out.print(mothTime);
           }
           HashMap<String,ArrayList<BigDecimal>> mothItemDate=new HashMap<>();
           mothItemDate.put("time",mothTimeList);
           mothItemDate.put("distance",mothDistanceList);
           mothDate.add(mothItemDate);


           mothItem.setDistance(mothDistance);
           mothItem.setEnergyExpenditure(mothExpenditure);
           mothItem.setEnergyMetabolism(mothMetabolism);
           mothItem.setTime(mothTime);
           if(mothTime>0) {
               mothItem.setSpeed(mothDistance.divide(new BigDecimal(mothTime), 0, BigDecimal.ROUND_DOWN));
           }else {
               weekItem.setSpeed(new BigDecimal(0));
           }
           String rate;
           if(sportWeekDataList.size()>=5){
               rate="频繁";
           }else if(sportWeekDataList.size()>=2){
               rate="中";
           }else{
               rate="低";
           }
           todayItem.setRate(rate);
           todayItem.setHabits(runCount>=4?"跑步":"散步");
           vo.setTodayItem(todayItem);
           vo.setWeekItem(weekItem);
           vo.setMothItem(mothItem);
           vo.setWeek(weekDate);
           vo.setMoth(mothDate);
           vo.setUserimg(user.getImage());//用户头像
           return vo;
       }
       return null;
   }*/

    public int dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min
                    + "分钟" + sec + "秒。");
            if (day>=1) {
                return (int)day;
            }else {
                return 0;

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }
    @Override
    public List<SportData> getSportWeekList() {
        Date startWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(6);
        Date endWeekDate = DateUtil.getBeforeNDays0H0M0S0MS(-1);
        List<SportData> sportWeekDataList = sportDataMapper.queryAll(startWeekDate, endWeekDate);
        return sportWeekDataList;
    }

    @Override
    public SportData queryByBaiduUid(String baiduUid) {
        Date date=DateUtil.getBeforeNDays0H0M0S0MS(0);
        SportData sportData=sportDataMapper.queryByBaiduUid(date,baiduUid);
        return sportData;
    }

    @Override
    public int insertSelective(SportData sportData) {
        return sportDataMapper.insertSelective(sportData);
    }

    /**
     * y运动三十天历史记录
     * @param openid
     * @return
     */
    @Override
    public List<SportData> selectThirtyDaysHistory(String openid) {
        return sportDataMapper.selectThirtyDaysHistory(openid);
    }
}
