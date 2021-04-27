package com.mmtax.business.mapper;

import com.mmtax.business.domain.PetSaleRecord;
import com.mmtax.business.dto.AddPetSaleDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.common.utils.MyMapper;

import java.util.List;

/**
 * 销售宠物记录 数据层
 * 
 * @author meimiao
 * @date 2021-04-27
 */
public interface PetSaleRecordMapper extends MyMapper<PetSaleRecord>
{
    List<PetInfoDTO> selectPetSaleRecordList(PetInfoQueryDTO dto);
}