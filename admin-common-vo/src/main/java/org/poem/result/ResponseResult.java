package org.poem.result;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author poem
 */
public class ResponseResult<T> implements Serializable {

    @ApiModelProperty(value = "业务状态码")
    private Integer code;

    @ApiModelProperty(value = "有效数据")
    private T data;

    @ApiModelProperty(value = "消息")
    private String message;

    public ResponseResult() {
        this.code = 0;
    }


    public ResponseResult(T data) {
        this.data = data;
        this.code = 0;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
