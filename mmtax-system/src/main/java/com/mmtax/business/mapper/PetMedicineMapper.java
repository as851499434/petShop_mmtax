package com.mmtax.business.mapper;

import com.mmtax.business.domain.PetMedicine;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.common.utils.MyMapper;

import java.util.List;

/**
 * 宠物药品 数据层
 * 
 * @author meimiao
 * @date 2021-04-29
 */
public interface PetMedicineMapper extends MyMapper<PetMedicine>
{
    List<PetGoodDTO> selectPetMedicineList(PetGoodQueryDTO dto);
}