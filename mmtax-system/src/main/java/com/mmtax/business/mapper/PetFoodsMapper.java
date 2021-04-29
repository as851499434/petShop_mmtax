package com.mmtax.business.mapper;

import com.mmtax.business.domain.PetFoods;
import com.mmtax.business.domain.PetToys;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.common.utils.MyMapper;

import java.util.List;

/**
 * 宠物食品 数据层
 * 
 * @author meimiao
 * @date 2021-04-29
 */
public interface PetFoodsMapper extends MyMapper<PetFoods>
{
    List<PetGoodDTO> selectPetFoodsList(PetGoodQueryDTO dto);
}