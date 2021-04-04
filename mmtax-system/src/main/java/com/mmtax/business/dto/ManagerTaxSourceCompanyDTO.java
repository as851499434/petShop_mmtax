package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/12 16:53
 */
public class ManagerTaxSourceCompanyDTO {
    @ApiModelProperty("税源地公司id")
    private Integer id;
    /**
     * 税源地公司名称
     */
    @ApiModelProperty("税源地公司名称")
    private String taxSounrceCompanyName;
    /**
     * 渠道CHANPAY-畅捷
     */
    @ApiModelProperty("渠道CHANPAY-畅捷,YUNZB-云众包")
    private String channel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaxSounrceCompanyName() {
        return taxSounrceCompanyName;
    }

    public void setTaxSounrceCompanyName(String taxSounrceCompanyName) {
        this.taxSounrceCompanyName = taxSounrceCompanyName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
