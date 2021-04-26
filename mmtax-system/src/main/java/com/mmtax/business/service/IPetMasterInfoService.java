package com.mmtax.business.service;

import com.mmtax.business.domain.PetMasterInfo;
import com.mmtax.business.dto.PetInfoQueryDTO;

import java.util.List;

/**
 * 宠物主人 服务层
 * 
 * @author meimiao
 * @date 2021-04-10
 */
public interface IPetMasterInfoService
{
    List<PetMasterInfo> selectPetMasterInfoList(PetInfoQueryDTO queryDTO);

    int insertPetMasterInfo(PetMasterInfo petMasterInfo);

    PetMasterInfo selectPetMasterInfoById(Integer id);

    int updatePetMasterInfo(PetMasterInfo petMasterInfo);

    int deletePetMasterInfoByIds(String ids);

}
