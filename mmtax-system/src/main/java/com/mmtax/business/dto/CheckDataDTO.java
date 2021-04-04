package com.mmtax.business.dto;

import lombok.Data;

@Data
public class CheckDataDTO {
    private Integer id;
    /** 商户流水号 */
    private String merchantSerialNum;
    /** 姓名 */
    private String name;
    /** 手机号 */
    private String mobile;
    /** 银行卡号 */
    private String bankCard;
    /** 收款人身份证号 */
    private String idCardNo;
    /** 0-校验中 1-成功 2-失败 */
    private Integer status;
    /** 打款备注 */
    private String remark;
}
