package com.mmtax.business.service;

import com.mmtax.business.domain.PetInfo;
import com.mmtax.business.dto.AddPetAndMasterInfoDto;
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

    List<PetInfo> selectPetInfoList(PetInfoQueryDTO queryDTO);

    int insertPetInfo(AddPetAndMasterInfoDto dto);

    PetInfo selectPetInfoById(Integer id);

    int updatePetInfo(PetInfo petInfo);

    int deletePetInfoByIds(String ids);
}
