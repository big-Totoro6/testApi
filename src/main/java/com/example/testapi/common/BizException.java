package com.example.testapi.common;

public class BizException extends RuntimeException {

    private String code;
    private String message;

    public BizException(String message) {
        this.message = message;
    }

    // 构造函数
    public BizException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    // 获取错误码
    public String getCode() {
        return code;
    }

    // 获取错误信息
    @Override
    public String getMessage() {
        return message;
    }

    // 可选：根据错误码和信息来创建BizException的静态方法
    public static BizException of(String code, String message) {
        return new BizException(code, message);
    }
}
