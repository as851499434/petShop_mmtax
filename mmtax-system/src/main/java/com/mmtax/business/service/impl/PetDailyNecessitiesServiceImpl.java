package com.mmtax.business.service.impl;

import com.mmtax.business.domain.PetDailyNecessities;
import com.mmtax.business.domain.PetMedicine;
import com.mmtax.business.dto.AddPetDailyNecessitiesDTO;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.business.mapper.PetDailyNecessitiesMapper;
import com.mmtax.common.enums.DelStatusEnum;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPetDailyNecessitiesService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 宠物日用品 服务层实现
 * 
 * @author meimiao
 * @date 2021-04-29
 */
@Service
public class PetDailyNecessitiesServiceImpl implements IPetDailyNecessitiesService
{
    @Resource
    PetDailyNecessitiesMapper petDailyNecessitiesMapper;
    @Override
    public List<PetGoodDTO> selectPetDailyNecessitiesList(PetGoodQueryDTO dto) {
        List<PetGoodDTO> petGoodDTOS = petDailyNecessitiesMapper.selectPetDailyNecessitiesList(dto);
        //计算到期时间 和 剩余时间
        for (PetGoodDTO petGoodDTO : petGoodDTOS) {
            Date date = new Date();
            long endTime = petGoodDTO.getProductionTime().getTime() + 86400000L * petGoodDTO.getShelfLife();
            Date end = new Date(endTime);
            String datePoor = PetFosterRecordServiceImpl.getDatePoor(end, new Date());
            petGoodDTO.setEndTime(end);
            petGoodDTO.setRemainTime(datePoor);
        }
        return petGoodDTOS;
    }

    @Override
    public int insertPetDailyNecessities(AddPetDailyNecessitiesDTO dto) {
        PetDailyNecessities petDailyNecessities = new PetDailyNecessities();
        petDailyNecessities.setName(dto.getName());
        petDailyNecessities.setNumber(dto.getNumber());
        petDailyNecessities.setPrice(new BigDecimal(dto.getPrice()));
        petDailyNecessities.setCost(new BigDecimal(dto.getCost()));
        petDailyNecessities.setFactory(dto.getFactory());
        petDailyNecessities.setRemake(dto.getRemake());
        petDailyNecessities.setProductionTime(dto.getProductionTime());
        petDailyNecessities.setShelfLife(dto.getShelfLife());
        petDailyNecessities.setDelStatus(DelStatusEnum.NORMAL.getCode());
        petDailyNecessities.setCreateTime(new Date());
        petDailyNecessities.setUpdateTime(new Date());
        petDailyNecessitiesMapper.insertSelective(petDailyNecessities);
        return 1;
    }

    @Override
    public PetDailyNecessities selectPetDailyNecessitiesById(Integer id) {
        PetDailyNecessities petDailyNecessities = petDailyNecessitiesMapper.selectByPrimaryKey(id);
        return petDailyNecessities;
    }

    @Override
    public int updatePetDailyNecessities(PetDailyNecessities petDailyNecessities) {
        petDailyNecessities.setUpdateTime(new Date());
        petDailyNecessitiesMapper.updateByPrimaryKeySelective(petDailyNecessities);
        return 1;
    }

    @Override
    public int deletePetDailyNecessitiesByIds(String ids) {
        List<PetDailyNecessities> infos = petDailyNecessitiesMapper.selectByIds(ids);
        for (PetDailyNecessities info : infos) {
            info.setDelStatus(DelStatusEnum.DELETE.getCode());
            info.setUpdateTime(new Date());
            petDailyNecessitiesMapper.updateByPrimaryKey(info);
        }
        return 1;
    }
}
