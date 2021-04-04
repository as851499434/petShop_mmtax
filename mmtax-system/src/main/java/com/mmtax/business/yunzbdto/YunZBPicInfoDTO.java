package com.mmtax.business.yunzbdto;

import com.mmtax.common.utils.yunzbutil.BaseRequest;

/**
 * 上传图片
 *
 * @Author: wangzhaoxu
 * @Date: 2019/11/8 16:30
 */
public class YunZBPicInfoDTO extends BaseRequest {

    /**
     * 二级子商户号
     */
    private String subMchId;

    /**
     * 营业执照
     */
    private String licence;

    /**
     * 法人身份证正面
     */
    private String idCardFront;

    /**
     * 法人身份证反面
     */
    private String idCardBack;

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }
}
