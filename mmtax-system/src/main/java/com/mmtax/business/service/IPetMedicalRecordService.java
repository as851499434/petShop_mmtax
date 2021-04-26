package com.mmtax.business.service;

import com.mmtax.business.domain.PetMedicalRecord;
import com.mmtax.business.dto.AddPetMedicalDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;

import java.util.List;

/**
 * 医疗宠物病历 服务层
 * 
 * @author meimiao
 * @date 2021-04-26
 */
public interface IPetMedicalRecordService
{

    List<PetInfoDTO> selectPetMedicalRecordList(PetInfoQueryDTO queryDTO);

    int insertPetMedicalRecord(AddPetMedicalDTO dto);

    PetMedicalRecord selectPetMedicalRecordById(Integer id);

    int updatePetMedicalRecord(PetMedicalRecord petMedicalRecord);

    int deletePetMedicalRecordByIds(String ids);
}
