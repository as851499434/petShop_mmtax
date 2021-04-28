package com.mmtax.business.service.impl;

import com.mmtax.business.domain.PetInfo;
import com.mmtax.business.domain.PetMasterInfo;
import com.mmtax.business.domain.PetSaleRecord;
import com.mmtax.business.domain.PetServiceRecord;
import com.mmtax.business.dto.AddPetServiceDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.business.mapper.PetInfoMapper;
import com.mmtax.business.mapper.PetMasterInfoMapper;
import com.mmtax.business.mapper.PetSaleRecordMapper;
import com.mmtax.business.mapper.PetServiceRecordMapper;
import com.mmtax.common.enums.DelStatusEnum;
import com.mmtax.common.exception.BusinessException;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPetServiceRecordService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 宠物服务记录 服务层实现
 * 
 * @author meimiao
 * @date 2021-04-28
 */
@Service
public class PetServiceRecordServiceImpl implements IPetServiceRecordService
{
    @Resource
    PetServiceRecordMapper petServiceRecordMapper;
    @Resource
    PetInfoMapper petInfoMapper;
    @Resource
    PetMasterInfoMapper petMasterInfoMapper;
    @Override
    public List<PetInfoDTO> selectPetServiceRecordList(PetInfoQueryDTO queryDTO) {
        List<PetInfoDTO> petInfoDTOS = petServiceRecordMapper.selectPetServiceRecordList(queryDTO);
        return petInfoDTOS;
    }

    @Override
    public int insertPetServiceRecord(AddPetServiceDTO dto) {
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
        petInfo.setPetInfoType(5);
        petInfo.setCreateTime(new Date());
        petInfo.setUpdateTime(new Date());
        petInfoMapper.insertSelective(petInfo);
        //添加宠物销售信息
        PetServiceRecord petServiceRecord = new PetServiceRecord();
        petServiceRecord.setPetInfoId(petInfo.getId());

        petServiceRecord.setPrice(new BigDecimal(dto.getPrice()));
        petServiceRecord.setService(dto.getService());

        petServiceRecord.setRemake(dto.getRemake());
        petServiceRecord.setDelStatus(DelStatusEnum.NORMAL.getCode());
        petServiceRecord.setCreateTime(new Date());
        petServiceRecord.setUpdateTime(new Date());
        petServiceRecordMapper.insertSelective(petServiceRecord);
        return 1;
    }

    @Override
    public PetServiceRecord selectPetServiceRecordById(Integer id) {
        PetServiceRecord petServiceRecord = petServiceRecordMapper.selectByPrimaryKey(id);
        return petServiceRecord;
    }

    @Override
    public int updatePetServiceRecord(PetServiceRecord petServiceRecord) {
        petServiceRecord.setUpdateTime(new Date());
        petServiceRecordMapper.updateByPrimaryKeySelective(petServiceRecord);
        return 1;
    }

    @Override
    public int deletePetServiceRecordByIds(String ids) {
        List<PetServiceRecord> infos = petServiceRecordMapper.selectByIds(ids);
        for (PetServiceRecord info : infos) {
            info.setDelStatus(DelStatusEnum.DELETE.getCode());
            info.setUpdateTime(new Date());
            petServiceRecordMapper.updateByPrimaryKey(info);
        }
        return 1;
    }
}
