package com.yuanju.demo.enums;

/**
 * Created by sqc95 on 2018/7/8.
 */
public enum UserEnum {
        HAVE_REGISTER("该手机账号已注册", 10000),
        HAVE_NOT_REGISTER("该手机账号尚未注册", 10001),
        REGISTER_SUCCESS("注册成功",10002),
        PHONE_FORMAT_ERROR("手机号格式错误",10003),
        PASSWORD_EXIST("已进行过密码初始化", 10010),
        PASSWORD_NOT_EXIST("未进行过密码初始化", 10011),
        USER_IDCARD_ERROR("身份证号码格式错误",10101),
        FILE_FORMAT_ERROR("上传文件格式错误",10203),
        CODE_ERROR("验证码错误",10052),
        CODE_FORMAT_ERROR("验证码格式错误",10053),
        INIT_PASSWORD_SUCCESS("密码初始化/短信重设密码成功",10020),
        INIT_PASSWORD_ERROR("密码初始化/短信重设密码失败",10021) ,
        PASSWORD_RESET_SUCCESS("密码重置成功",10030),
        OLD_PASSWORD_ERROR("原始密码输入错误",10031),
        DIFFERENT_NEW_PASSWORD("两次输入的新密码不同",10032),
        WRONG_PASSWORD_LENGTH("密码长度应设置在6~12之间",10033),
        LOGIN_ERROR("账号或密码错误",10050),
        LOGIN_SUCCESS("用户登陆成功",10051),
        //通用异常
        COMMONS_SELEC_SUCCESS("查询成功",50001),
        COMMONS_SELEC_ERROR("查询失败",50002),

        //登录拦截器
        OTHER_PLACE_LOGIN("您的账号已在其他地点登陆",10087),
        OVERDUE_LOGIN("超时，请重新登录",10086);


        private String msg;
        private int code;

    UserEnum(final String msg, final int code) {
            this.msg = msg;
            this.code = code;
        }


        public int getCode() {
            return this.code;
        }

        public String getMsg(){
            return this.msg;
        }
    }





