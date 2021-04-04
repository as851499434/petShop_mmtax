package com.mmtax.business.yunzbdto;

import com.mmtax.common.utils.yunzbutil.BaseRequest;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/13 10:08
 */
public class YunZBQueryInvoiceDTO extends BaseRequest {


    private String batchId;

    private String channelNo;

    private String subMchId;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }
}
