package com.mmtax.business.service.impl;

import com.mmtax.business.dto.INvoiceHandleOldDataDTO;
import com.mmtax.business.mapper.INvoiceHandleOldDataMapper;
import com.mmtax.business.service.INoviceHandleOldDataService;
import com.mmtax.common.constant.RegularExpressionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class INvoiceHandleOldDataServiceImpl implements INoviceHandleOldDataService {

    @Resource
    INvoiceHandleOldDataMapper iNvoiceHandleOldDataMapper;

    @Override
    public void noviceHandleOldData() {
        List<INvoiceHandleOldDataDTO> tempINvoiceHandleOldDataDTO = new ArrayList<>();
        List<INvoiceHandleOldDataDTO> iNvoiceHandleOldDataDTOS = iNvoiceHandleOldDataMapper.selectAllNoviceHandleOldDataDTO();
        for (INvoiceHandleOldDataDTO iNvoiceHandleOldDataDTO:iNvoiceHandleOldDataDTOS) {
            String bankNumberTemp = iNvoiceHandleOldDataDTO.getBankNumber();
            iNvoiceHandleOldDataDTO.setBankNumber(bankNumberTemp.replaceAll(RegularExpressionConstants.BANK_NUMBER,"").trim());
            iNvoiceHandleOldDataDTO.setBankName(bankNumberTemp.replaceAll(RegularExpressionConstants.BANK_NAME,"").trim());
            tempINvoiceHandleOldDataDTO.add(iNvoiceHandleOldDataDTO);
        }
        iNvoiceHandleOldDataMapper.updateInoviceByMerchantId(tempINvoiceHandleOldDataDTO);
    }
}
