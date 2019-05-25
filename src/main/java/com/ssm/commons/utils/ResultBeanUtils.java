package com.ssm.commons.utils;

import com.ssm.commons.bean.ResultBean;
import com.ssm.commons.constants.Codes;
import com.ssm.commons.constants.Messages;

public class ResultBeanUtils {

    //查询成功
    public static <T> ResultBean successQuery(T data) {
        ResultBean<T> bean = new ResultBean<>(Codes.SUCCESS, true, Messages.SUCCESS_QUERY, data);
        return bean;
    }

    //更新成功
    public static <T> ResultBean successUpdate(T data) {
        ResultBean<T> bean = new ResultBean<>(Codes.SUCCESS, true, Messages.SUCCESS_UPDATE, data);
        return bean;
    }

    //更新成功
    public static <T> ResultBean successUpdate() {
        ResultBean<T> bean = new ResultBean<>(Codes.SUCCESS, true, Messages.SUCCESS_UPDATE, null);
        return bean;
    }

    //失败
    public static ResultBean error(int code, String message) {
        ResultBean bean = new ResultBean<>(code, false, message, null);
        return bean;
    }
}
