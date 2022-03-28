package com.wg.basics.common;

public enum ResponseResult implements IErrorCode{

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    FORBIDDEN(403,"没有相关权限"),
    UNAUTHORIZED(401,"需要进行身份认证");
    private long status;
    private String message;

    ResponseResult(long status, String message) {
        this.status = status;
        this.message = message;
    }


    @Override
    public long getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
