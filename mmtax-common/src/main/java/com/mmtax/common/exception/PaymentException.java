package com.mmtax.common.exception;

import com.mmtax.common.enums.InternetBusinessFailReasonEnum;
import com.mmtax.common.enums.PaymentErrorCodeEnum;
import com.mmtax.common.utils.EnumUtil;
import lombok.Data;

@Data
public class PaymentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected final PaymentErrorCodeEnum errorCode;

    protected final InternetBusinessFailReasonEnum errorCodeAnother;

    private String code;

    /**
     * 指定错误码构造通用异常
     * @param errorCode
     */
    public PaymentException(final PaymentErrorCodeEnum errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.errorCodeAnother = null;
        this.code = errorCode.getCode();
    }

    /**
     * 指定第三方错误码构造通用返回异常
     * @param errorCodeAnother
     */
    public PaymentException(final InternetBusinessFailReasonEnum errorCodeAnother) {
        super(errorCodeAnother.getModifyDescription());
        this.errorCodeAnother = errorCodeAnother;
        this.errorCode = null;
        this.code = "500";
    }

    /**
     * 指定详细错误构造异常
     * @param detailMessage
     */
    public PaymentException(final String detailMessage) {
        super(detailMessage);
        this.errorCode = PaymentErrorCodeEnum.UNSPECIFIED;
        this.errorCodeAnother = null;
        this.code = errorCode.getCode();
    }

    /**
     * 指定错误码添加详细说明
     * @param errorCode
     * @param detailMessage
     */
    public PaymentException(final PaymentErrorCodeEnum errorCode,final String detailMessage) {
        super(errorCode.getDescription()+"："+detailMessage);
        this.errorCode = errorCode;
        this.errorCodeAnother = null;
        this.code = errorCode.getCode();
    }

    public PaymentErrorCodeEnum getErrorCode() {
        return errorCode;
    }
}
