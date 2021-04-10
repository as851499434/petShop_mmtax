package com.mmtax.business.service;

import com.mmtax.business.domain.PetMasterInfo;
import java.util.List;

/**
 * 宠物主人 服务层
 * 
 * @author meimiao
 * @date 2021-04-10
 */
public interface IPetMasterInfoService
{
    List<PetMasterInfo> selectPetMasterInfoList(PetMasterInfo petMasterInfo);

    int insertPetMasterInfo(PetMasterInfo petMasterInfo);

    PetMasterInfo selectPetMasterInfoById(Integer id);

    int updatePetMasterInfo(PetMasterInfo petMasterInfo);

    int deletePetMasterInfoByIds(String ids);

}
