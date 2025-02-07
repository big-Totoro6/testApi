package com.example.testapi.common;

public class PlatformException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    // 构造函数
    public PlatformException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public PlatformException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    // 获取错误码
    public String getErrorCode() {
        return errorCode;
    }

    // 获取错误信息
    @Override
    public String getMessage() {
        return errorMessage;
    }

    // 可选：根据错误码和信息来创建PlatformException的静态方法
    public static PlatformException of(String errorCode, String errorMessage) {
        return new PlatformException(errorCode, errorMessage);
    }
}
