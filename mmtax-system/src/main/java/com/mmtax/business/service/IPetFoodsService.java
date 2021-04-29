package com.mmtax.business.service;

import com.mmtax.business.domain.PetFoods;
import com.mmtax.business.domain.PetToys;
import com.mmtax.business.dto.AddPetFoodDTO;
import com.mmtax.business.dto.AddPetToyDTO;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;

import java.util.List;

/**
 * 宠物食品 服务层
 * 
 * @author meimiao
 * @date 2021-04-29
 */
public interface IPetFoodsService
{
    List<PetGoodDTO> selectPetFoodsList(PetGoodQueryDTO dto);

    int insertPetFoods(AddPetFoodDTO dto);

    PetFoods selectPetFoodsById(Integer id);

    int updatePetFoods(PetFoods petFoods);

    int deletePetFoodsByIds(String ids);
}
