package com.mmtax.business.service;

import com.mmtax.business.domain.PetDailyNecessities;
import com.mmtax.business.domain.PetMedicine;
import com.mmtax.business.dto.AddPetDailyNecessitiesDTO;
import com.mmtax.business.dto.AddPetMedicineDTO;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;

import java.util.List;

/**
 * 宠物日用品 服务层
 * 
 * @author meimiao
 * @date 2021-04-29
 */
public interface IPetDailyNecessitiesService
{
    List<PetGoodDTO> selectPetDailyNecessitiesList(PetGoodQueryDTO dto);

    int insertPetDailyNecessities(AddPetDailyNecessitiesDTO dto);

    PetDailyNecessities selectPetDailyNecessitiesById(Integer id);

    int updatePetDailyNecessities(PetDailyNecessities petDailyNecessities);

    int deletePetDailyNecessitiesByIds(String ids);
}
