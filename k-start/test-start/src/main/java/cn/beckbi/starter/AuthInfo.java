package cn.beckbi.starter;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-06-27 19:38
 */
public class AuthInfo {

    private AuthInfoProperties authInfoProperties;

    public AuthInfo(){}

    public AuthInfo(AuthInfoProperties authInfoProperties){
        this.authInfoProperties = authInfoProperties;
    }

    public String getUser(){
        return authInfoProperties.getUser();
    }

    public String getToken(){
        return authInfoProperties.getToken();
    }


}
