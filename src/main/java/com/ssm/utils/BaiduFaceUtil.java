package com.ssm.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人脸检测与属性分析
 */
public class BaiduFaceUtil {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     * 人脸库检查
     */
    public static String detect(String type,String value) {

        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
            Map<String, Object> map = new HashMap<>();
//            byte[] myfile=FileUtil.readFileByBytes("D:/myimg.jpg");
//            String imgbase64=Base64Util.encode(myfile);
            map.put("image", value);
            map.put("face_field", "faceshape,facetype");
            map.put("image_type", type);

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String ak="B1o5rSxO2ZGTPxKncNtpadeW";
            String sk="QCtGOFdS6xhFgZFlQrthpy93gx6GrT5O";
            String accessToken = BaiDuAI_AccessToken.getAuth(ak,sk);

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加用户图像到百度云
     * @param type
     * @param value
     * @param uid
     * @return
     */
    public static String add(String type,String value,String uid) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", value);
            map.put("group_id", "faceStorage_02");
            map.put("user_id", uid);
            map.put("image_type", type);

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String ak="B1o5rSxO2ZGTPxKncNtpadeW";
            String sk="QCtGOFdS6xhFgZFlQrthpy93gx6GrT5O";
            String accessToken = BaiDuAI_AccessToken.getAuth(ak,sk);

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String search(String type,String value) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", value);
            map.put("group_id_list", "faceStorage_02");
            map.put("image_type", type);

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String ak="B1o5rSxO2ZGTPxKncNtpadeW";
            String sk="QCtGOFdS6xhFgZFlQrthpy93gx6GrT5O";
            String accessToken = BaiDuAI_AccessToken.getAuth(ak,sk);

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String match(String imageStart,String imageEnd) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            List<Map<String, Object>> images = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", imageStart);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", imageEnd);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NORMAL");

            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String ak="B1o5rSxO2ZGTPxKncNtpadeW";
            String sk="QCtGOFdS6xhFgZFlQrthpy93gx6GrT5O";
            String accessToken = BaiDuAI_AccessToken.getAuth(ak,sk);

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    public static void main(String[] args) {
//        new BaiduFaceUtil().detect();
//    }
}