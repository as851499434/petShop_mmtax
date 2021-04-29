package com.mmtax.business.mapper;

import com.mmtax.business.domain.PetDailyNecessities;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.common.utils.MyMapper;

import java.util.List;

/**
 * 宠物日用品 数据层
 * 
 * @author meimiao
 * @date 2021-04-29
 */
public interface PetDailyNecessitiesMapper extends MyMapper<PetDailyNecessities>
{
    List<PetGoodDTO> selectPetDailyNecessitiesList(PetGoodQueryDTO dto);
}