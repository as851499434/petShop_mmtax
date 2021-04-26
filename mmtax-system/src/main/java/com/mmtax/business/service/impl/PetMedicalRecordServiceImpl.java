package com.mmtax.business.service.impl;

import com.mmtax.business.domain.PetInfo;
import com.mmtax.business.domain.PetMasterInfo;
import com.mmtax.business.domain.PetMedicalRecord;
import com.mmtax.business.dto.AddPetMedicalDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.business.mapper.PetInfoMapper;
import com.mmtax.business.mapper.PetMasterInfoMapper;
import com.mmtax.business.mapper.PetMedicalRecordMapper;
import com.mmtax.common.enums.DelStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPetMedicalRecordService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 医疗宠物病历 服务层实现
 * 
 * @author meimiao
 * @date 2021-04-26
 */
@Service
public class PetMedicalRecordServiceImpl implements IPetMedicalRecordService
{
    @Resource
    PetMedicalRecordMapper petMedicalRecordMapper;
    @Resource
    PetInfoMapper petInfoMapper;
    @Resource
    PetMasterInfoMapper petMasterInfoMapper;

    @Override
    public List<PetInfoDTO> selectPetMedicalRecordList(PetInfoQueryDTO queryDTO) {
        List<PetInfoDTO> infos = petMedicalRecordMapper.selectPetMedicalRecordList(queryDTO);
        return infos;
    }

    @Override
    public int insertPetMedicalRecord(AddPetMedicalDTO dto) {
        if (null == dto) {
            throw new BusinessException("传入数据为空");
        }
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
        petInfo.setPetInfoType(1);
        petInfo.setRemake(dto.getRemake());
        petInfo.setCreateTime(new Date());
        petInfo.setUpdateTime(new Date());
        petInfoMapper.insertSelective(petInfo);
        //添加宠物病历信息
        PetMedicalRecord petMedicalRecord = new PetMedicalRecord();
        petMedicalRecord.setPetInfoId(petInfo.getId());
        petMedicalRecord.setDisease(dto.getDisease());
        petMedicalRecord.setMethod(dto.getMethod());
        petMedicalRecord.setRemake(dto.getRemake());
        petMedicalRecord.setDelStatus(DelStatusEnum.NORMAL.getCode());
        petMedicalRecord.setCreateTime(new Date());
        petMedicalRecord.setUpdateTime(new Date());
        petMedicalRecordMapper.insertSelective(petMedicalRecord);
        return 1;
    }

    @Override
    public PetMedicalRecord selectPetMedicalRecordById(Integer id) {
        PetMedicalRecord petMedicalRecord = petMedicalRecordMapper.selectByPrimaryKey(id);
        return petMedicalRecord;
    }

    @Override
    public int updatePetMedicalRecord(PetMedicalRecord petMedicalRecord) {
        petMedicalRecordMapper.updateByPrimaryKey(petMedicalRecord);
        return 1;
    }

    @Override
    public int deletePetMedicalRecordByIds(String ids) {
        List<PetMedicalRecord> infos = petMedicalRecordMapper.selectByIds(ids);
        for (PetMedicalRecord info : infos) {
            info.setDelStatus(DelStatusEnum.DELETE.getCode());
            info.setUpdateTime(new Date());
            petMedicalRecordMapper.updateByPrimaryKey(info);
        }
        return 1;
    }
}
