package com.mmtax.business.service;

import com.mmtax.business.domain.PetInfo;
import com.mmtax.business.dto.AddPetAndMasterInfoDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;

import java.util.List;

/**
 * 宠物 服务层
 * 
 * @author meimiao
 * @date 2021-04-10
 */
public interface IPetInfoService
{

    List<PetInfoDTO> selectPetInfoList(PetInfoQueryDTO queryDTO);

    int insertPetInfo(AddPetAndMasterInfoDTO dto);

    PetInfo selectPetInfoById(Integer id);

    int updatePetInfo(PetInfo petInfo);

    int deletePetInfoByIds(String ids);
}
