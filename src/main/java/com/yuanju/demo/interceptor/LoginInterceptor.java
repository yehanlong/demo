package com.yuanju.demo.interceptor;


import com.gexin.fastjson.JSON;
import com.yuanju.demo.po.UserPO;
import com.yuanju.demo.service.common.RedisService;
import com.yuanju.demo.utils.ResultResp;
import com.yuanju.demo.utils.SpringContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.yuanju.demo.enums.ConstantClassField.REDIS_TOKEN_VAILD;
import static com.yuanju.demo.enums.ConstantClassField.REDIS_USER_SESSION_KEY;
import static com.yuanju.demo.enums.UserEnum.OTHER_PLACE_LOGIN;
import static com.yuanju.demo.enums.UserEnum.OVERDUE_LOGIN;


/**
 * 登陆拦截与跨域处理
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if(token==null||token.equals("")){
            printJson(response, "");
            return false;
        }
        //超时登陆和异地登陆
        UserPO user = (UserPO) getRedisService().get(REDIS_USER_SESSION_KEY+":"+token);
        if(user==null){
            String a = (String)getRedisService().get(REDIS_TOKEN_VAILD+REDIS_USER_SESSION_KEY+":"+token);
            if(a==null){
                printJson2(response, "");
            }else{
                printJson(response, "");
            }
            return false;
        }
        return true;
        }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

//        vary: Origin
//        Vary: Access-Control-Request-Method
//        Vary: Access-Control-Request-Headers
//        Access-Control-Allow-Origin: *

}

    private static void printJson(HttpServletResponse response, String code) {
        ResultResp responseResult = ResultResp.getResult(10086,false, OVERDUE_LOGIN.getMsg(),null);
        String content = JSON.toJSONString(responseResult);
        printContent(response, content);
    }


    private static void printJson2(HttpServletResponse response, String code) {
        ResultResp responseResult = ResultResp.getResult(10087,false,OTHER_PLACE_LOGIN.getMsg(),null);
        String content = JSON.toJSONString(responseResult);
        printContent(response, content);
    }
    private RedisService getRedisService(){
        return SpringContextHolder.getBean(RedisService.class);
    }
    private static void printContent(HttpServletResponse response, String content) {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "token");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Max-Age", "3600");
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

