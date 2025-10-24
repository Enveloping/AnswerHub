package com.Answer.AnswerHub.constants;

public class AnswerHubConstants {
    /**
     * 微信用户登录字符常量
     */
    public static final class WX_LOGIN {
        public static final String OPEN_ID = "openid";
        public static final String APP_ID = "appid";
        public static final String APP_SECRET = "secret";
        public static final String JS_CODE = "js_code";
        public static final String GRANT_TYPE = "grant_type";
        public static final String GRANT_TYPE_VALUE = "authorization_code";
        public static final String USER_TYPE = "user_type";

    }

    public static final class AUTHORITY{
        public static final String STUDENT = "role_student";
        public static final String TEACHER = "role_teacher";
        public static final String ADMIN = "role_admin";
    }

    public static final class REDIS_KEY{
        public static final String LOGIN_KEY = "AnswerHub:user:login_token";
    }
}
