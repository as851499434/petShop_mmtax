package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商户修改密码DTO
 * @author yuanligang
 * @date 2019/7/14
 */
public class UpdatePasswordDTO {

    /**
     * 商户编号
     */
    @ApiModelProperty(value = "商户编号", required = true)
    private Integer merchantId;

    /**
     * 原密码
     */
    @ApiModelProperty(value = "原密码", required = true)
    private String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UpdatePasswordDTO{" +
                "merchantId='" + merchantId + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
