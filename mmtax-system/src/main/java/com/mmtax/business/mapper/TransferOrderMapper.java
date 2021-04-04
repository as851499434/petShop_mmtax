package com.mmtax.business.mapper;

import com.mmtax.business.domain.TransferOrder;
import com.mmtax.business.dto.IncomeWithdrawDTO;
import com.mmtax.business.dto.QuaryTransferOrderinfoDTO;
import com.mmtax.business.dto.TransferOrderinfoDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商户转账员工记录 数据层
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Repository
public interface TransferOrderMapper extends MyMapper<TransferOrder>
{
    List<TransferOrder> selectByAsyncStatus(@Param("asyncStatus") Integer asyncStatus);

    TransferOrder selectByTransferSerialNum(@Param("transferSerialNum") String transferSerialNum);

    TransferOrder selectByOrderSerialNumAndStatus(@Param("orderSerialNum") String orderSerialNum
            ,@Param("status") Integer status);

    List<TransferOrderinfoDTO> listTranferOrderInfoDTO(QuaryTransferOrderinfoDTO quaryDTO);

    List<TransferOrder> selectInitOrProcess(@Param("createTime") String createTime);
}