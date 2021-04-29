package com.mmtax.business.service.impl;

import com.mmtax.business.domain.PetFoods;
import com.mmtax.business.domain.PetMedicine;
import com.mmtax.business.dto.AddPetFoodDTO;
import com.mmtax.business.dto.AddPetMedicineDTO;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.business.mapper.PetMedicineMapper;
import com.mmtax.common.enums.DelStatusEnum;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPetMedicineService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 宠物药品 服务层实现
 * 
 * @author meimiao
 * @date 2021-04-29
 */
@Service
public class PetMedicineServiceImpl implements IPetMedicineService
{
    @Resource
    PetMedicineMapper petMedicineMapper;

    @Override
    public List<PetGoodDTO> selectPetMedicineList(PetGoodQueryDTO dto) {
        List<PetGoodDTO> petGoodDTOS = petMedicineMapper.selectPetMedicineList(dto);
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
    public int insertPetMedicine(AddPetMedicineDTO dto) {
        PetMedicine petMedicine = new PetMedicine();
        petMedicine.setName(dto.getName());
        petMedicine.setNumber(dto.getNumber());
        petMedicine.setPrice(new BigDecimal(dto.getPrice()));
        petMedicine.setCost(new BigDecimal(dto.getCost()));
        petMedicine.setFactory(dto.getFactory());
        petMedicine.setRemake(dto.getRemake());
        petMedicine.setProductionTime(dto.getProductionTime());
        petMedicine.setShelfLife(dto.getShelfLife());
        petMedicine.setDelStatus(DelStatusEnum.NORMAL.getCode());
        petMedicine.setCreateTime(new Date());
        petMedicine.setUpdateTime(new Date());
        petMedicineMapper.insertSelective(petMedicine);
        return 1;
    }

    @Override
    public PetMedicine selectPetMedicineById(Integer id) {
        PetMedicine petMedicine = petMedicineMapper.selectByPrimaryKey(id);
        return petMedicine;
    }

    @Override
    public int updatePetMedicine(PetMedicine petMedicine) {
        petMedicine.setUpdateTime(new Date());
        petMedicineMapper.updateByPrimaryKeySelective(petMedicine);
        return 1;
    }

    @Override
    public int deletePetMedicineByIds(String ids) {
        List<PetMedicine> infos = petMedicineMapper.selectByIds(ids);
        for (PetMedicine info : infos) {
            info.setDelStatus(DelStatusEnum.DELETE.getCode());
            info.setUpdateTime(new Date());
            petMedicineMapper.updateByPrimaryKey(info);
        }
        return 1;
    }
}
