package com.mmtax.common.utils.onlinebank;

import java.util.Date;
import java.util.List;

/**
 * 网商账户余额对账结果集
 * @author Ljd
 * @date 2020/6/20
 */
public class AccountCheckOnlineDTO {
    /**
     * 核对时间
     */
    private Date checkDate;
    List<AccountCheckResultOnLineDTO> resultOnLineDTOList;

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public List<AccountCheckResultOnLineDTO> getResultOnLineDTOList() {
        return resultOnLineDTOList;
    }

    public void setResultOnLineDTOList(List<AccountCheckResultOnLineDTO> resultOnLineDTOList) {
        this.resultOnLineDTOList = resultOnLineDTOList;
    }
}
