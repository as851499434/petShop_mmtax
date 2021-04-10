package com.mmtax.business.service.impl;

import com.mmtax.business.domain.PetInfo;
import com.mmtax.business.domain.PetMasterInfo;
import com.mmtax.business.dto.AddPetAndMasterInfoDto;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.business.mapper.PetInfoMapper;
import com.mmtax.business.mapper.PetMasterInfoMapper;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPetInfoService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 宠物 服务层实现
 * 
 * @author meimiao
 * @date 2021-04-10
 */
@Service
public class PetInfoServiceImpl implements IPetInfoService
{
    @Resource
    PetInfoMapper petInfoMapper;
    @Resource
    PetMasterInfoMapper petMasterInfoMapper;

    @Override
    public List<PetInfo> selectPetInfoList(PetInfoQueryDTO queryDTO) {
        return petInfoMapper.selectPetInfoList(queryDTO);
    }

    @Override
    public int insertPetInfo(AddPetAndMasterInfoDto dto) {
        if (null == dto) {
            throw new BusinessException("传入数据为空");
        }
        //添加宠物主人信息
        PetMasterInfo petMasterInfo = new PetMasterInfo();
        petMasterInfo.setName(dto.getName());
        petMasterInfo.setAge(dto.getAge());
        petMasterInfo.setSex(dto.getSex());
        petMasterInfo.setPhonenumber(dto.getPhonenumber());
        petMasterInfo.setEmail(dto.getEmail());
        petMasterInfo.setAdress(dto.getAdress());
        petMasterInfo.setCreateTime(new Date());
        petMasterInfo.setUpdateTime(new Date());
        petMasterInfoMapper.insertSelective(petMasterInfo);
        //添加宠物信息
        PetInfo petInfo = new PetInfo();
        petInfo.setMasterId(petMasterInfo.getId());
        petInfo.setPetName(dto.getPetName());
        petInfo.setPetSex(dto.getPetSex());
        petInfo.setPetAge(dto.getPetAge());
        petInfo.setPetType(dto.getPetType());
        petInfo.setPetInfoType(dto.getPetInfoType());
        petInfo.setRemake(dto.getRemake());
        petInfo.setCreateTime(new Date());
        petInfo.setUpdateTime(new Date());
        petInfoMapper.insertSelective(petInfo);
        return 1;
    }

    @Override
    public PetInfo selectPetInfoById(Integer id) {
        return null;
    }

    @Override
    public int updatePetInfo(PetInfo petInfo) {
        return 0;
    }

    @Override
    public int deletePetInfoByIds(String ids) {
        return 0;
    }

    private void checkPetInfo(PetInfo petInfo){
        if (StringUtils.isEmpty(petInfo.getPetName())) {
            throw new BusinessException("请输入宠物名字");
        }
        if (StringUtils.isEmpty(petInfo.getPetType())) {
            throw new BusinessException("请输入宠物种类");
        }
        if (StringUtils.isEmpty(petInfo.getPetSex().toString())) {
            throw new BusinessException("请输入宠物性别");
        }
        if (StringUtils.isEmpty(petInfo.getPetAge().toString())) {
            throw new BusinessException("请输入年龄");
        }
    }

    private void checkMasterInfo(PetMasterInfo info){
        if (StringUtils.isEmpty(info.getName())) {
            throw new BusinessException("请输入姓名");
        }
        if (StringUtils.isEmpty(info.getAge().toString())) {
            throw new BusinessException("请输入年龄");
        }
        if (StringUtils.isEmpty(info.getSex())) {
            throw new BusinessException("请输入性别");
        }
        if (StringUtils.isEmpty(info.getPhonenumber())) {
            throw new BusinessException("请输入手机号");
        }
        if (StringUtils.isEmpty(info.getEmail())) {
            throw new BusinessException("请输入邮箱号码");
        }
    }
}
