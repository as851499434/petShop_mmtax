package com.mmtax.business.service;

import com.mmtax.business.domain.PetClothes;
import com.mmtax.business.domain.PetDailyNecessities;
import com.mmtax.business.dto.AddPetClothesDTO;
import com.mmtax.business.dto.AddPetDailyNecessitiesDTO;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;

import java.util.List;

/**
 * 宠物服饰 服务层
 * 
 * @author meimiao
 * @date 2021-04-29
 */
public interface IPetClothesService
{
    List<PetGoodDTO> selectPetClothesList(PetGoodQueryDTO dto);

    int insertPetClothes(AddPetClothesDTO dto);

    PetClothes selectPetClothesById(Integer id);

    int updatePetClothes(PetClothes petClothes);

    int deletePetClothesByIds(String ids);
}
