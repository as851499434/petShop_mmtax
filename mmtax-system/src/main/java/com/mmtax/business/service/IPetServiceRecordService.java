package com.mmtax.business.service;

import com.mmtax.business.domain.PetSaleRecord;
import com.mmtax.business.domain.PetServiceRecord;
import com.mmtax.business.dto.AddPetSaleDTO;
import com.mmtax.business.dto.AddPetServiceDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;

import java.util.List;

/**
 * 宠物服务记录 服务层
 * 
 * @author meimiao
 * @date 2021-04-28
 */
public interface IPetServiceRecordService
{
    List<PetInfoDTO> selectPetServiceRecordList(PetInfoQueryDTO queryDTO);

    int insertPetServiceRecord(AddPetServiceDTO dto);

    PetServiceRecord selectPetServiceRecordById(Integer id);

    int updatePetServiceRecord(PetServiceRecord petServiceRecord);

    int deletePetServiceRecordByIds(String ids);
}
