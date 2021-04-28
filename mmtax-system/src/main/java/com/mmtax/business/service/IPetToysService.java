package com.mmtax.business.service;

import com.mmtax.business.domain.PetServiceRecord;
import com.mmtax.business.domain.PetToys;
import com.mmtax.business.dto.*;

import java.util.List;

/**
 * 宠物玩具 服务层
 * 
 * @author meimiao
 * @date 2021-04-29
 */
public interface IPetToysService
{
    List<PetToys> selectPetToysList(PetGoodQueryDTO dto);

    int insertPetToys(AddPetToyDTO dto);

    PetToys selectPetToysById(Integer id);

    int updatePetToys(PetToys petToys);

    int deletePetToysByIds(String ids);
}
