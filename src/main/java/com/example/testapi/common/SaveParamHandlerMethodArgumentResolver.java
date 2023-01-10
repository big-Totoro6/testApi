package com.example.testapi.common;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

/**
 * @Service被容器扫描建立bean   （声明组件
 */
@Service
public class SaveParamHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //确定是咱支持的类型 咱就去给它处理解析 (SaveData类 并且有 SaveParam注解的)
        return parameter.getParameterType().isAssignableFrom(SaveData.class)&&parameter.hasParameterAnnotation(SaveParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String data = webRequest.getParameter("data");
        JSONObject stringObjectMap = JSON.parseObject(data);
        SaveData detailResult=new SaveData();
        for (String key:stringObjectMap.keySet()){
            JSONObject value = (JSONObject) stringObjectMap.get(key);//key是表名
            if (value.get("form")!=null){
                detailResult.getMst().put(key, value.toString());
            }
            if (value.get("table")!=null){
                detailResult.getDetail().put(key, value.toString());
            }
        }
        return detailResult;
    }
}
