package com.wg.basics.common;

public interface IErrorCode {
    /**
     * 获取状态码
     * @return
     */
    long getStatus();

    /**
     * 获取信息
     * @return
     */
    String getMessage();
}
