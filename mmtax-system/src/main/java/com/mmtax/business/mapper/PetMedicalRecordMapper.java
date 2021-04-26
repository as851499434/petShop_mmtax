package com.mmtax.business.mapper;

import com.mmtax.business.domain.PetMedicalRecord;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.common.utils.MyMapper;

import java.util.List;

/**
 * 医疗宠物病历 数据层
 * 
 * @author meimiao
 * @date 2021-04-26
 */
public interface PetMedicalRecordMapper extends MyMapper<PetMedicalRecord>
{
    List<PetInfoDTO> selectPetMedicalRecordList(PetInfoQueryDTO queryDTO);
}