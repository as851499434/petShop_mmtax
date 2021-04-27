package com.mmtax.business.service;

import com.mmtax.business.domain.PetFosterRecord;
import com.mmtax.business.dto.AddPetFosterDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;

import java.util.List;

/**
 * 宠物寄养记录 服务层
 * 
 * @author meimiao
 * @date 2021-04-27
 */
public interface IPetFosterRecordService
{
    List<PetInfoDTO> selectPetFosterRecordList(PetInfoQueryDTO dto);

    int insertPetFosterRecord(AddPetFosterDTO dto);

    PetFosterRecord selectPetFosterRecordById(Integer id);

    int updatePetFosterRecord(PetFosterRecord petFosterRecord);

    int deletePetFosterRecordByIds(String ids);
}
