package com.mmtax.business.mapper;

import com.mmtax.business.domain.PetClothes;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.common.utils.MyMapper;

import java.util.List;

/**
 * 宠物服饰 数据层
 * 
 * @author meimiao
 * @date 2021-04-29
 */
public interface PetClothesMapper extends MyMapper<PetClothes>
{
   List<PetGoodDTO> selectPetClothesList(PetGoodQueryDTO dto);
}