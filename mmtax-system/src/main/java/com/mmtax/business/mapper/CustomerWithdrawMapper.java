package com.mmtax.business.mapper;

import com.mmtax.business.domain.CustomerWithdraw;
import com.mmtax.business.dto.IncomeWithdrawDTO;
import com.mmtax.business.dto.CustomerWithdrawInfoDTO;
import com.mmtax.business.dto.QueryCustomerWithdrawInfoDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 灵工提现记录 数据层
 * 
 * @author meimiao
 * @date 2020-09-11
 */
@Repository
public interface CustomerWithdrawMapper extends MyMapper<CustomerWithdraw>
{
    List<CustomerWithdraw> selectByAsyncStatusWithdrawStatusBeforeTime(@Param("asyncStatus") Integer asyncStatus,
                                                                       @Param("withdrawStatus") Integer withdrawStatus,
                                                                       @Param("createTime") String createTime);

    CustomerWithdraw selectByWithdrawSerialNum(@Param("withdrawSerialNum") String withdrawSerialNum);

    List<IncomeWithdrawDTO> listPageByCustomerIdCreateTimeStatus(@Param("customerId") Integer customerId,
                                                                 @Param("status") Integer status,
                                                                 @Param("startDate") String startDate,
                                                                 @Param("endDate") String endDate,
                                                                 @Param("index") Integer index,
                                                                 @Param("pageSize") Integer pageSize);

    Integer countByCustomerIdCreateTimeStatus(@Param("customerId") Integer customerId,
                                              @Param("status") Integer status,
                                              @Param("startDate") String startDate,
                                              @Param("endDate") String endDate);

    BigDecimal sumAmountByCustomerId(@Param("startDate") String startDate, @Param("endDate") String endDate
            ,@Param("customerId") Integer customerId,@Param("status") Integer status
            ,@Param("withdrawAccount") String withdrawAccount);

    /**
     * 查询灵工提现记录列表
     * @param queryDTO
     * @return
     */
    List<CustomerWithdrawInfoDTO> listCustomerWithdrawInfo(QueryCustomerWithdrawInfoDTO queryDTO);

}