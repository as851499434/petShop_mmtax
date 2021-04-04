package com.mmtax.common.utils.onlinebank;

import lombok.Data;

import java.util.List;

@Data
public class BankcardQueryResultDTO extends OnlineCommonResultDTO {
    private List<BankcardInfo> bank_list;
}

@Data
class BankcardInfo{
    private String bank_id;
    private String bank_code;
    private String bank_name;
    private String bank_account_no;
    private String account_name;
    private String province;
    private String city;
    private String branch_no;
    private String bank_branch;
    private String reserved_mobile;
    private String certificate_no;
    private String certificate_type;
    private String is_verified;
}
