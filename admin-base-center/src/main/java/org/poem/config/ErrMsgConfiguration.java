package org.poem.config;

import com.alibaba.fastjson.JSONObject;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.poem.exception.ApiCenterProviderException;
import org.poem.exception.ApiCenterProviderRouteException;
import org.poem.result.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 处理下游服务的异常
 *
 * @author sangfor
 */
@ControllerAdvice
@Configuration
public class ErrMsgConfiguration implements ErrorDecoder {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ErrMsgConfiguration.class);

    /**
     * 下游的服务的异常的处理
     * @param s
     * @param response
     * @return
     */
    @Override
    public Exception decode(String s, Response response) {
        Exception exception = null;
        try {
            // 获取原始的返回内容
            String json = Util.toString(response.body().asReader());
            JSONObject jsonObject = JSONObject.parseObject(json);
            logger.error(json);
            int status =  jsonObject.getIntValue("status");
            String trace = jsonObject.getString("trace");
            String message = jsonObject.getString("message");
            if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                exception = new ApiCenterProviderException(message, new Throwable(trace));
            }else if(status == HttpStatus.NOT_FOUND.value()){
                exception = new ApiCenterProviderRouteException(message, new Throwable(trace));
            }else{
                exception = new RuntimeException(json);
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return exception;
    }

    /**
     * 500 服务内部错误
     * 这个注解是指当controller中抛出这个指定的异常类的时候，都会转到这个方法中来处理异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult<String> handlerUserNotExistException(Exception ex) {
        ResponseResult<String> ResponseResult = new ResponseResult<>();
        if (ex instanceof UndeclaredThrowableException){
            UndeclaredThrowableException throwableException = (UndeclaredThrowableException)ex;
            Throwable  throwable = throwableException.getUndeclaredThrowable();
            //下游的服务出现错误
            if(throwable instanceof ApiCenterProviderException){
                ApiCenterProviderException providerException = (ApiCenterProviderException) throwable;
                logger.error(providerException.getMessage(),providerException);
                ResponseResult.setCode(29);
                ResponseResult.setData(null);
                ResponseResult.setMessage("微服务出现异常，请稍后再试! ");
                return ResponseResult;
            }
            if (throwable instanceof ApiCenterProviderRouteException){
                ApiCenterProviderRouteException providerException = (ApiCenterProviderRouteException) throwable;
                logger.error(providerException.getMessage(),providerException);
                ResponseResult.setCode(30);
                ResponseResult.setData(null);
                ResponseResult.setMessage("联系管理员，微服务路径不正确!");
                return ResponseResult;
            }
        }
        logger.error(ex.getMessage(),ex);
        ResponseResult.setCode(28);
        ResponseResult.setData(null);
        ResponseResult.setMessage("微服务暂时还不可用，请稍后再试! ");
        return ResponseResult;
    }
}
