package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/12 13:54
 */
@ApiModel(value = "商户登录")
public class MerchantLoginDTO {
    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "验证码")
    private String safeCode;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSafeCode() {
        return safeCode;
    }

    public void setSafeCode(String safeCode) {
        this.safeCode = safeCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MerchantLoginDTO{" +
                "token='" + token + '\'' +
                ", safeCode='" + safeCode + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
