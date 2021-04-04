package com.mmtax.business.mapper;

import com.mmtax.business.domain.CustomerInfo;
import com.mmtax.business.dto.ManagerCustomerInfoDTO;
import com.mmtax.business.dto.ManagerCustomerInfoQueryDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工 数据层
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Repository
public interface CustomerInfoMapper extends MyMapper<CustomerInfo> {

    List<CustomerInfo> selectListByTimeAsc(@Param("idCardNo") String idCardNo);

    CustomerInfo selectByCustomerNo(@Param("customerNo") String customerNo);

    CustomerInfo selectByIdCardNoAndTaxSourceId(@Param("idCardNo") String idCardNo
            ,@Param("taxSourceId") Integer taxSourceId);

    /**
     * 获取员工列表数据
     * @param queryDTO 查询条件
     * @return 列表信息
     */
    List<ManagerCustomerInfoDTO> listManagerCustomerInfoDTO(ManagerCustomerInfoQueryDTO queryDTO);

    /**
     * 获取 同一员工 在多家 商户注册的
     * @param certificateNo
     * @return
     */
    List<CustomerInfo> listCustomerRegisterMultiMerchant(@Param("certificateNo")Integer certificateNo);

    int updateCustomerWarn(@Param("customerWarn")Integer customerWarn,@Param("customerId")Integer customerId);
}