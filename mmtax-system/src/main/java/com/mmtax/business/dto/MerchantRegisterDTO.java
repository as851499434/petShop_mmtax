package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/14 15:38
 */
public class MerchantRegisterDTO {
    @ApiModelProperty(value = "邮箱地址")
    private String email;
    @ApiModelProperty(value = "验证码")
    private String verificationCode;
    @ApiModelProperty(value = "邀请码")
    private String invitationCode;
    @ApiModelProperty(value = "登录密码")
    private String loginPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
