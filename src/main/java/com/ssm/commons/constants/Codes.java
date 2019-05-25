package com.ssm.commons.constants;

public interface Codes {
    int SUCCESS = 200; //成功
    int ERROR_HTTP_MESSAGE_NOT_READABLE = 400; //POST请求body缺失
    int ERROR_METHOD = 403; //请求方法错误
    int UNKNOWN_ERROR = 500; //未知错误

    int ERROR_PARAM = 600;
    int ERROR_PARAM_TYPE = 601;
}
