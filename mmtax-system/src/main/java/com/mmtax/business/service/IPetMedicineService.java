package com.mmtax.business.service;

import com.mmtax.business.domain.PetFoods;
import com.mmtax.business.domain.PetMedicine;
import com.mmtax.business.dto.AddPetFoodDTO;
import com.mmtax.business.dto.AddPetMedicineDTO;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;

import java.util.List;

/**
 * 宠物药品 服务层
 * 
 * @author meimiao
 * @date 2021-04-29
 */
public interface IPetMedicineService
{
    List<PetGoodDTO> selectPetMedicineList(PetGoodQueryDTO dto);

    int insertPetMedicine(AddPetMedicineDTO dto);

    PetMedicine selectPetMedicineById(Integer id);

    int updatePetMedicine(PetMedicine petMedicine);

    int deletePetMedicineByIds(String ids);
}
