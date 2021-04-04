package com.mmtax.business.mapper;

import com.mmtax.business.dto.INvoiceHandleOldDataDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface INvoiceHandleOldDataMapper  {
    /**
     * 查询tbl_invoice_info 表的merchant_id和bank_number
     * @return
     */
    List<INvoiceHandleOldDataDTO> selectAllNoviceHandleOldDataDTO();

    /**
     * 根据merchantId修改tbl_invoice_info表的bank_number和bank_name
     * @param iNvoiceHandleOldDataDTOS
     * @return
     */
    boolean updateInoviceByMerchantId(@Param("iNvoiceHandleOldDataDTOS") List<INvoiceHandleOldDataDTO> iNvoiceHandleOldDataDTOS);
}
