package com.mmtax.business.service.impl;

import com.mmtax.business.domain.PetMasterInfo;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPetMasterInfoService;

import java.util.List;

/**
 * 宠物主人 服务层实现
 * 
 * @author meimiao
 * @date 2021-04-10
 */
@Service
public class PetMasterInfoServiceImpl implements IPetMasterInfoService
{

    @Override
    public List<PetMasterInfo> selectPetMasterInfoList(PetMasterInfo petMasterInfo) {
        return null;
    }

    @Override
    public int insertPetMasterInfo(PetMasterInfo petMasterInfo) {
        return 0;
    }

    @Override
    public PetMasterInfo selectPetMasterInfoById(Integer id) {
        return null;
    }

    @Override
    public int updatePetMasterInfo(PetMasterInfo petMasterInfo) {
        return 0;
    }

    @Override
    public int deletePetMasterInfoByIds(String ids) {
        return 0;
    }
}
