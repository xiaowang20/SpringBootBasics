package com.wg.basics.common;

import lombok.Data;

@Data
public class CommonResult<T> {
    private long status;
    private String message;
    private T data;

    public CommonResult() {
    }

    public CommonResult(long status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 200，成功
     * @param data
     * @param <T>
     * @return
     */
        public static <T> CommonResult<T> success(T data){
            return new CommonResult<T>(ResponseResult.SUCCESS.getStatus(),ResponseResult.SUCCESS.getMessage(),data);
    }

    /**
     * 403错误，没有相关权限
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> forbidden(T data){
            return new CommonResult<T>(ResponseResult.FORBIDDEN.getStatus(),ResponseResult.FORBIDDEN.getMessage(),data);
    }

    /**
     * 401,认证失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unauthorized(T data){
        return new CommonResult<T>(ResponseResult.UNAUTHORIZED.getStatus(),ResponseResult.UNAUTHORIZED.getMessage(),data);
    }

    /**
     * 404,登录验证失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> validateFailed(T data){
        return new CommonResult<T>(ResponseResult.VALIDATEfAILED.getStatus(),ResponseResult.VALIDATEfAILED.getMessage(),data);
    }

    /**
     * 505,服务器不支持请求
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(T data){
        return new CommonResult<T>(ResponseResult.FAILED.getStatus(),ResponseResult.FAILED.getMessage(),data);
    }

}
