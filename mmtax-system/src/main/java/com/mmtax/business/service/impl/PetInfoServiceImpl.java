package com.mmtax.business.service.impl;

import com.mmtax.business.domain.PetInfo;
import com.mmtax.business.domain.PetMasterInfo;
import com.mmtax.business.dto.AddPetAndMasterInfoDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.business.mapper.PetInfoMapper;
import com.mmtax.business.mapper.PetMasterInfoMapper;
import com.mmtax.common.enums.DelStatusEnum;
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
    public List<PetInfoDTO> selectPetInfoList(PetInfoQueryDTO queryDTO) {
        return petInfoMapper.selectPetInfoList(queryDTO);
    }

    @Override
    public int insertPetInfo(AddPetAndMasterInfoDTO dto) {
        if (null == dto) {
            throw new BusinessException("传入数据为空");
        }
        checkPetInfo(dto);
        //查找宠物主人信息
        PetMasterInfo petMasterInfo = new PetMasterInfo();
        petMasterInfo.setPhonenumber(dto.getPhonenumber());
        PetMasterInfo masterInfo = petMasterInfoMapper.selectOne(petMasterInfo);
        if (null == masterInfo) {
            throw new BusinessException("未找到主人信息,请注册或检查信息是否正确");
        }
        //添加宠物信息
        PetInfo petInfo = new PetInfo();
        petInfo.setMasterId(masterInfo.getId());
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
        PetInfo petInfo = new PetInfo();
        petInfo.setId(id);
        PetInfo rPetInfo = petInfoMapper.selectOne(petInfo);
        return rPetInfo;
    }

    @Override
    public int updatePetInfo(PetInfo petInfo) {
        petInfo.setUpdateTime(new Date());
        int i = petInfoMapper.updateByPrimaryKeySelective(petInfo);
        return i;
    }

    @Override
    public int deletePetInfoByIds(String ids) {
        List<PetInfo> petInfos = petInfoMapper.selectByIds(ids);
        for (PetInfo petInfo : petInfos) {
            petInfo.setDelStatus(DelStatusEnum.DELETE.getCode().toString());
            petInfo.setUpdateTime(new Date());
            petInfoMapper.updateByPrimaryKeySelective(petInfo);
        }
        return 1;
    }

    private void checkPetInfo(AddPetAndMasterInfoDTO dto){
        //信息校验
        if (StringUtils.isEmpty(dto.getPhonenumber())) {
            throw new BusinessException("请输入主人手机号");
        }
        if (StringUtils.isEmpty(dto.getPetName())) {
            throw new BusinessException("请输入宠物名字");
        }
        if (StringUtils.isEmpty(dto.getPetType())) {
            throw new BusinessException("请输入宠物种类");
        }
        if (StringUtils.isEmpty(dto.getPetSex().toString())) {
            throw new BusinessException("请输入宠物性别");
        }
        if (StringUtils.isEmpty(dto.getPetAge().toString())) {
            throw new BusinessException("请输入年龄");
        }

    }

}
