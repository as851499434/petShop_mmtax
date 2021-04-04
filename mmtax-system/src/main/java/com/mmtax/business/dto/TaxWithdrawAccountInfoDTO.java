package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import lombok.Data;

@Data
public class TaxWithdrawAccountInfoDTO {
    @Excel(name = "税源地名称")
    private String taxSourceName;
    @Excel(name = "公司简称")
    private String companyName;
    /** 对公银行名称 */
    @Excel(name = "银行名称")
    private String bankPublic;
    /** 对公开户行 */
    @Excel(name = "支行名称")
    private String bankNamePublic;
    /** 对公银行户名 */
    @Excel(name = "对公银行分支行号")
    private String reservedFieldOne;
    @Excel(name = "户名")
    private String bankAccountPublic;
    /** 对公银行账号 */
    @Excel(name = "银行卡号")
    private String bankCardPublic;
    @Excel(name = "可提现状态")
    private String allowWithdraw;
    @Excel(name = "创建时间")
    private String createTime;
}
