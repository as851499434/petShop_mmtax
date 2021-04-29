package com.mmtax.business.service.impl;

import com.mmtax.business.domain.PetFosterRecord;
import com.mmtax.business.domain.PetInfo;
import com.mmtax.business.domain.PetMasterInfo;
import com.mmtax.business.domain.PetSaleRecord;
import com.mmtax.business.dto.AddPetFosterDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.business.mapper.PetFosterRecordMapper;
import com.mmtax.business.mapper.PetInfoMapper;
import com.mmtax.business.mapper.PetMasterInfoMapper;
import com.mmtax.business.mapper.PetSaleRecordMapper;
import com.mmtax.common.enums.DelStatusEnum;
import com.mmtax.common.exception.BusinessException;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPetFosterRecordService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 宠物寄养记录 服务层实现
 * 
 * @author meimiao
 * @date 2021-04-27
 */
@Service
public class PetFosterRecordServiceImpl implements IPetFosterRecordService
{
    @Resource
    PetFosterRecordMapper petFosterRecordMapper;
    @Resource
    PetInfoMapper petInfoMapper;
    @Resource
    PetMasterInfoMapper petMasterInfoMapper;

    @Override
    public List<PetInfoDTO> selectPetFosterRecordList(PetInfoQueryDTO dto) {
        List<PetInfoDTO> petInfoDTOS = petFosterRecordMapper.selectPetFosterRecordList(dto);
        for (PetInfoDTO petInfoDTO : petInfoDTOS) {
            long endTime = petInfoDTO.getCreateTime().getTime() + 86400000L * Integer.parseInt(petInfoDTO.getDay());
            Date end = new Date(endTime);
            String datePoor = getDatePoor(end, new Date());
            petInfoDTO.setEndTime(end);
            petInfoDTO.setRemainTime(datePoor);
        }
        return petInfoDTOS;
    }

    @Override
    public int insertPetFosterRecord(AddPetFosterDTO dto) {
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
        petInfo.setPetInfoType(3);
        petInfo.setCreateTime(new Date());
        petInfo.setUpdateTime(new Date());
        petInfoMapper.insertSelective(petInfo);
        //添加宠物销售信息
        PetFosterRecord petFosterRecord = new PetFosterRecord();
        petFosterRecord.setPetInfoId(petInfo.getId());

        petFosterRecord.setPrice(new BigDecimal(dto.getPrice()));
        petFosterRecord.setDay(dto.getDay());

        petFosterRecord.setRemake(dto.getRemake());
        petFosterRecord.setDelStatus(DelStatusEnum.NORMAL.getCode());
        petFosterRecord.setCreateTime(new Date());
        petFosterRecord.setUpdateTime(new Date());
        petFosterRecordMapper.insertSelective(petFosterRecord);
        return 1;
    }

    @Override
    public PetFosterRecord selectPetFosterRecordById(Integer id) {
        PetFosterRecord petFosterRecord = petFosterRecordMapper.selectByPrimaryKey(id);
        return petFosterRecord;
    }

    @Override
    public int updatePetFosterRecord(PetFosterRecord petFosterRecord) {
        petFosterRecord.setUpdateTime(new Date());
        petFosterRecordMapper.updateByPrimaryKeySelective(petFosterRecord);
        return 1;
    }

    @Override
    public int deletePetFosterRecordByIds(String ids) {
        List<PetFosterRecord> infos = petFosterRecordMapper.selectByIds(ids);
        for (PetFosterRecord info : infos) {
            info.setDelStatus(DelStatusEnum.DELETE.getCode());
            info.setUpdateTime(new Date());
            petFosterRecordMapper.updateByPrimaryKey(info);
        }
        return 1;
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        if (nowDate.getTime() > endDate.getTime()) {
            return "0" + "天" + "0" + "小时" + "0" + "分钟";
        }
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
}
