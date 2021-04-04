package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class BankcardUnbindDTO extends OnlineBankCommonDTO {
    private String uid;
    private String bankId;
}
