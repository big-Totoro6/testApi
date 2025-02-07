package com.example.testapi.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Map<Class<? extends Throwable>, Function<Throwable, ResponseEntity>> exceptionHandlerMap = new HashMap<>();

    /**
     * 例如，业务异常（BizException）可能表示用户操作不符合业务规则，这通常会导致请求失败，返回一个 HTTP 错误码。
     * 而平台异常（PlatformException）则可能表示系统错误，比如数据库或网络故障，这种异常通常会触发全局异常处理并返回一个 500 错误。
     */
    static {
        // 初始化Map，异常类型和对应的处理函数
        exceptionHandlerMap.put(BizException.class, GlobalExceptionHandler::handleBizException);
        exceptionHandlerMap.put(PlatformException.class, GlobalExceptionHandler::handlePlatformException);

        // 可以继续添加其他异常和处理方法
    }

    // 捕获所有Exception 统一的异常处理方法
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleException(Throwable ex) {
        // 根据异常类型找到对应的处理函数
        Function<Throwable, ResponseEntity> handler = exceptionHandlerMap.getOrDefault(ex.getClass(), GlobalExceptionHandler::handleUnKnownException);
        ResponseEntity responseEntity = handler.apply(ex);
        return responseEntity;


    }
    private static ResponseEntity<String> handleUnKnownException(Throwable ex) {
        return new ResponseEntity<>("UnKnownException: 未定义的异常" + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 处理 BizException
    private static ResponseEntity<String> handleBizException(Throwable ex) {
        return new ResponseEntity<>("BizException: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 处理 PlatformException
    private static ResponseEntity<String> handlePlatformException(Throwable ex) {
        return new ResponseEntity<>("PlatformException: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
