package com.demo.gmall.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/5 21:56
 **/

@Configuration
public class AuthConfig {

    public static final String WB_ACCESSTOKEN_URL = "https://api.weibo.com/oauth2/access_token?";

    public static final String WB_CODE_URL = "https://api.weibo.com/oauth2/authorize?";

    public static final String WB_USERINFO_URL = "https://api.weibo.com/2/users/show.json?access_token=";

    public static  String WB_ACCESSTOKEN_= "";

    public static final String grant_type = "authorization_code";

    public static String wb_client_id = "187638711";

    public static String wb_client_secret = "a79777bba04ac70d973ee002d27ed58c";

    public static final String redirect_uri = "http://passport.gmall.com:8085/vlogin";

    public static final String response_type = "code";

    public static  String wb_code = "";

    public static  String wb_uid = "";

    public static final  String wb_source_type = "2";

}
