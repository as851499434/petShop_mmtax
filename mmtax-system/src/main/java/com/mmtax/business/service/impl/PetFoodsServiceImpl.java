package com.mmtax.business.service.impl;

import com.mmtax.business.domain.PetFoods;
import com.mmtax.business.domain.PetToys;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.PetFoodsMapper;
import com.mmtax.business.mapper.PetInfoMapper;
import com.mmtax.business.mapper.PetMasterInfoMapper;
import com.mmtax.common.enums.DelStatusEnum;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPetFoodsService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 宠物食品 服务层实现
 * 
 * @author meimiao
 * @date 2021-04-29
 */
@Service
public class PetFoodsServiceImpl implements IPetFoodsService
{
    @Resource
    PetFoodsMapper petFoodsMapper;
    @Resource
    PetInfoMapper petInfoMapper;
    @Resource
    PetMasterInfoMapper petMasterInfoMapper;

    @Override
    public List<PetGoodDTO> selectPetFoodsList(PetGoodQueryDTO dto) {
        List<PetGoodDTO> petGoodDTOS = petFoodsMapper.selectPetFoodsList(dto);
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
    public int insertPetFoods(AddPetFoodDTO dto) {
        PetFoods petFoods = new PetFoods();
        petFoods.setName(dto.getName());
        petFoods.setNumber(dto.getNumber());
        petFoods.setPrice(new BigDecimal(dto.getPrice()));
        petFoods.setCost(new BigDecimal(dto.getCost()));
        petFoods.setFactory(dto.getFactory());
        petFoods.setRemake(dto.getRemake());
        petFoods.setProductionTime(dto.getProductionTime());
        petFoods.setShelfLife(dto.getShelfLife());
        petFoods.setDelStatus(DelStatusEnum.NORMAL.getCode());
        petFoods.setCreateTime(new Date());
        petFoods.setUpdateTime(new Date());
        petFoodsMapper.insertSelective(petFoods);
        return 1;
    }

    @Override
    public PetFoods selectPetFoodsById(Integer id) {
        PetFoods petFoods = petFoodsMapper.selectByPrimaryKey(id);
        return petFoods;
    }

    @Override
    public int updatePetFoods(PetFoods petFoods) {
        petFoods.setUpdateTime(new Date());
        petFoodsMapper.updateByPrimaryKeySelective(petFoods);
        return 1;
    }

    @Override
    public int deletePetFoodsByIds(String ids) {
        List<PetFoods> infos = petFoodsMapper.selectByIds(ids);
        for (PetFoods info : infos) {
            info.setDelStatus(DelStatusEnum.DELETE.getCode());
            info.setUpdateTime(new Date());
            petFoodsMapper.updateByPrimaryKey(info);
        }
        return 1;
    }
}
