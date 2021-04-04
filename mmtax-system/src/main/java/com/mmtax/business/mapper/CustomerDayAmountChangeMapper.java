package com.mmtax.business.mapper;

import com.mmtax.business.domain.CustomerDayAmountChange;
import com.mmtax.business.dto.IncomeWithdrawDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 灵工每日金额变化 数据层
 * 
 * @author meimiao
 * @date 2020-09-21
 */
@Repository
public interface CustomerDayAmountChangeMapper extends MyMapper<CustomerDayAmountChange> {
    List<IncomeWithdrawDTO> selectListByTime(@Param("customerId") Integer customerId,@Param("startDate") String startDate
            , @Param("endDate") String endDate);
}