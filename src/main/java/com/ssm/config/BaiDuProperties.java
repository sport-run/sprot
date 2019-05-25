package com.ssm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 百度AI 配置文件
 *
 * @Auther: hancl
 * @Date: 2019-05-08 17:36
 * @Description:
 */
@Configuration
public class BaiDuProperties {

    //官网获取的 API Key
    @Value("${clientAPIKey}")
    public String clientAPIKey;
    //官网获取的 Secret Key
    @Value("${clientSecretKey}")
    public String clientSecretKey;
}
