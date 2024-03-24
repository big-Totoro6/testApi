package com.example.testapi.service.impl;

import com.example.testapi.common.Context;
import com.example.testapi.common.RedPaper;
import com.example.testapi.common.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class QueryGrantTypeService {
//    public String getResult(String resourceType) {
//        String grantType = "";
//        /**
//         * 策略模式是把 if语句里面的逻辑抽出来写成一个类，如果要修改某个逻辑的话，
//         * 仅修改一个具体实现类的逻辑即可，可维护性会好不少。
//         * 如果 if-else的判断情况很多，那么对应的具体策略实现类也会很多，上边的具体的策略实现类还只是2个，
//         * 查询红包发放方式写在类RedPaper里边，购物券写在另一个类Shopping里边；
//         * 那资源类型多个QQ会员和外卖会员，不就得再多写两个类？有点麻烦了
//         */
//        switch (resourceType) {
//            case "红包":
//                // 查询红包的派发方式
//                grantType = new Context(new RedPaper()).contextInterface();
//                return grantType;
//            case "购物券":
//                //  查询购物券的派发方式
//                grantType = new Context(new Shopping()).contextInterface();
//                return grantType;
//            case "QQ会员":
//                break;
//            case "外卖会员":
//                break;
//            default:
//                System.out.println("查找不到该优惠券类型resourceType以及对应的派发方式");
//                break;
//        }
//        return "";
//    }

    /**
     * 使用map+函数式接口
     */

    @Autowired
    private GrantTypeSerive grantTypeSerive;
    private Map<String, Function<String,String>> grantTypeMap=new HashMap<>();

    /**
     *  初始化业务分派逻辑,代替了if-else部分
     *  key: 优惠券类型
     *  value: lambda表达式,最终会获得该优惠券的发放方式
     */
    @PostConstruct
    public void dispatcherInit(){
        grantTypeMap.put("红包",resourceId->grantTypeSerive.redPaper(resourceId));
        grantTypeMap.put("购物券",resourceId->grantTypeSerive.shopping(resourceId));
        grantTypeMap.put("qq会员",resourceId->grantTypeSerive.QQVip(resourceId));
    }

    public String getResult(String resourceType){
        //Controller根据 优惠券类型resourceType、编码resourceId 去查询 发放方式grantType
        Function<String,String> result=grantTypeMap.get(resourceType);
        if(result!=null){
            //传入resourceId 执行这段表达式获得String型的grantType
            //入参String resourceId是用来查数据库的，这里简化了，传参之后不做处理。
//            return result.apply(resourceId);
            return result.apply("1");
        }
        return "查询不到该优惠券的发放方式";
    }
}
