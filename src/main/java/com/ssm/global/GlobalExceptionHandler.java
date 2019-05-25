package com.ssm.global;

import com.ssm.commons.bean.ResultBean;
import com.ssm.commons.constants.Codes;
import com.ssm.commons.utils.ResultBeanUtils;
import com.ssm.global.exception.MyException;
import com.ssm.utils.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;
import java.net.UnknownHostException;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultBean handle(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        if (CollectionUtil.isNotEmpty(fieldErrors)) {
            FieldError first = fieldErrors.get(0);
            // 为了复用原生的校验器 并且 兼容本项目的国际化，所以在使用时把 message 当作 code 传递过来
            String codeStr = first.getDefaultMessage();
            try {
                String[] parma = codeStr.split(",");
                if (parma.length == 1) {
                    return ResultBeanUtils.error(Codes.ERROR_PARAM, parma[0]);
                } else {
                    Integer code = Integer.parseInt(parma[0]);
                    String message = "";
                    if (parma.length > 1) {
                        message = parma[1];
                    }
                    return ResultBeanUtils.error(code, message);
                }
            } catch (NumberFormatException e1) {
                log.error("参数校验传入错误码格式不正确，应为[数字]型字符串，实际为[{}]", codeStr);
                return ResultBeanUtils.error(Codes.ERROR_PARAM,
                        "参数校验传入错误码格式不正确，应为[数字]型字符串，实际为" + codeStr);
            }
        } else {
            return ResultBeanUtils.error(Codes.ERROR_PARAM, "参数格式有误");
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultBean handle(HttpMessageNotReadableException e) {
        log.warn("POST 请求缺失 body 信息");
        return ResultBeanUtils.error(Codes.ERROR_HTTP_MESSAGE_NOT_READABLE, "请求缺失 body 信息");
    }

    //统一处理异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean handle(HttpServletRequest request, Exception e) {

        //自定义异常
        if (e instanceof MyException) {
            MyException exception = (MyException) e;
            log.error("异常：code=" + exception.getErrorCode() + " , message=" + exception.getMessage());
            return ResultBeanUtils.error(exception.getErrorCode(), e.getMessage());
        } else if (e instanceof UnknownHostException) {
            log.error("异常：code=" + Codes.UNKNOWN_ERROR + " , message=" + "访问host不存在！");
            return ResultBeanUtils.error(Codes.UNKNOWN_ERROR, "访问host不存在！");
        } else if (e instanceof NoHandlerFoundException) {
            log.error("异常：code=" + Codes.UNKNOWN_ERROR + " , message=" + "访问路径不存在或者访问方式不正确！");
            return ResultBeanUtils.error(Codes.UNKNOWN_ERROR, "访问路径不存在或者访问方式不正确！");
        } else if (e instanceof UnexpectedTypeException || e instanceof ClassCastException) {
            log.error("异常：code=" + Codes.ERROR_PARAM + " , message=" + "参数类型不正确！" + " , " + e);
            return ResultBeanUtils.error(Codes.ERROR_PARAM_TYPE, "参数类型不正确！");
        } else if (e instanceof MissingServletRequestParameterException){
            return ResultBeanUtils.error(Codes.ERROR_PARAM, "缺少参数！");
        }
        log.error("异常：code=" + Codes.UNKNOWN_ERROR, e);
        //未知异常
        return ResultBeanUtils.error(Codes.UNKNOWN_ERROR, "服务器内部错误！");
    }
}
