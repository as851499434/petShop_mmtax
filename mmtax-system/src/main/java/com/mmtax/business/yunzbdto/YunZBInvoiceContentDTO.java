package com.mmtax.business.yunzbdto;

import com.mmtax.common.utils.yunzbutil.BaseRequest;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/12 14:51
 */
public class YunZBInvoiceContentDTO extends BaseRequest {
    /**
     * 子商户号
     */
    private String subMchId;

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }
}
