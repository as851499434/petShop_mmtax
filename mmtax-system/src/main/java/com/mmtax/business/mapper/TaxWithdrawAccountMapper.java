package com.mmtax.business.mapper;

import com.mmtax.business.domain.TaxWithdrawAccount;
import com.mmtax.business.dto.TaxWithdrawAccountDTO;
import com.mmtax.common.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 税源地提现账户 数据层
 * 
 * @author meimiao
 * @date 2020-06-29
 */
@Repository
public interface TaxWithdrawAccountMapper extends MyMapper<TaxWithdrawAccount>
{
    List<TaxWithdrawAccount> selectByAttributes(TaxWithdrawAccountDTO taxWithdrawAccountDTO);

}