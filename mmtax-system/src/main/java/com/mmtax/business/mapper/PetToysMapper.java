package com.mmtax.business.mapper;

import com.mmtax.business.domain.PetToys;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.common.utils.MyMapper;

import java.util.List;

/**
 * 宠物玩具 数据层
 * 
 * @author meimiao
 * @date 2021-04-29
 */
public interface PetToysMapper extends MyMapper<PetToys>
{
    List<PetToys> selectPetToysList(PetGoodQueryDTO dto);
}