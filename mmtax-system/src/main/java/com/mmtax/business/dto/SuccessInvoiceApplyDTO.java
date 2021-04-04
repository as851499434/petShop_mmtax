package com.mmtax.business.dto;

import com.mmtax.common.page.Page;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 成功开票DTO即发票详情菜单栏
 * @author yuanligang
 * @date 2019/7/16
 */
public class SuccessInvoiceApplyDTO {
    /**
     * 开票成功记录条
     */
    @ApiModelProperty(value = "开票成功记录条", required = true)
    private  Integer totalNum;
    /**
     * 开票单价合计
     */
    @ApiModelProperty(value = "开票单价合计", required = true)
    private  BigDecimal totalMoney;
    /**
     * 开票税额合计
     */
    @ApiModelProperty(value = "开票税额合计", required = true)
    private  BigDecimal totalTax;
    /**
     * 开票总金额合计
     */
    @ApiModelProperty(value = "开票总金额合计", required = true)
    private  BigDecimal totalAmount;
    /**
     * 开票 分页信息
     */
    private  Page page;

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "SuccessInvoiceApplyDTO{" +
                "totalNum=" + totalNum +
                ", totalMoney=" + totalMoney +
                ", totalTax=" + totalTax +
                ", totalAmount=" + totalAmount +
                ", page=" + page +
                '}';
    }
}
