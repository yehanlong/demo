package com.yuanju.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    private ValidationUtil() {
        throw new AssertionError("instaniation is not permitted");
    }

//针对传入的参数进行非空(null)校验并返回string数据类型--->只针对String类型，校验和赋值同时进行，同时返回真实的String值
    public static final String checkNullAndAssignString(final Object obj, final String...patterns){
        try {
            if (obj == null) {
                throw new NullPointerException();
            }
            String str=String.class.cast(obj);
            if(patterns.length!=0){//格式校验
                for (String pattern : patterns) {
                    if(str.matches(pattern)){
                        return str;
                    }
                }
                throw new RuntimeException("与指定格式不符");
            }
            return str;
        } catch (Exception e) {
            if(e instanceof NullPointerException){
                throw new NullPointerException("对象为null");
            }
            if(e instanceof ClassCastException){
                throw new ClassCastException("强转String类型异常");
            }
            throw new RuntimeException();
        }
    }

// 限定数值的范围,超出范围的会报异常,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以int类型返回
    public static final int checkRangeAndAssignInt(final Integer intValue, int min, int max, final String...patterns) {
        if (intValue < min) {
            throw new RuntimeException("最小值应该大于等于"+min);
        }
        if(intValue > max){
            throw new RuntimeException("最大值应该小于等于"+max);
        }
        if(patterns.length!=0){//格式校验
            for (String pattern : patterns) {
                if(String.valueOf(intValue).matches(pattern)){
                    return intValue;
                }
            }
            throw new RuntimeException("与指定格式不符");
        }
        return intValue;
    }
    //校验Integer
    public static final int checkMinAndAssignInt(final Integer intValue, int min,final String...patterns) {
        return checkRangeAndAssignInt(intValue,min,Integer.MAX_VALUE,patterns);
    }
//手机号码校验
public static boolean isMobileNO(String mobiles){
    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    Matcher m = p.matcher(mobiles);
    return m.matches();
}

}
