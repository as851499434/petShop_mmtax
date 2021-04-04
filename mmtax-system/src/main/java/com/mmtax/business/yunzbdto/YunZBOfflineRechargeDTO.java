package com.mmtax.business.yunzbdto;

import com.mmtax.common.utils.yunzbutil.BaseRequest;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/11 10:24
 */
public class YunZBOfflineRechargeDTO extends BaseRequest {

    private String subMchId;

    private String channelNo;

    private String amt;

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }
}
