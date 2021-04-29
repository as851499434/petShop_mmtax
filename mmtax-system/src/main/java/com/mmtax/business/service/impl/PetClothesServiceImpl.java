package com.mmtax.business.service.impl;

import com.mmtax.business.domain.PetClothes;
import com.mmtax.business.domain.PetMedicine;
import com.mmtax.business.dto.AddPetClothesDTO;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.business.mapper.PetClothesMapper;
import com.mmtax.common.enums.DelStatusEnum;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPetClothesService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 宠物服饰 服务层实现
 * 
 * @author meimiao
 * @date 2021-04-29
 */
@Service
public class PetClothesServiceImpl implements IPetClothesService
{
    @Resource
    PetClothesMapper petClothesMapper;
    @Override
    public List<PetGoodDTO> selectPetClothesList(PetGoodQueryDTO dto) {
        List<PetGoodDTO> petGoodDTOS = petClothesMapper.selectPetClothesList(dto);
        return petGoodDTOS;
    }

    @Override
    public int insertPetClothes(AddPetClothesDTO dto) {
        PetClothes petClothes = new PetClothes();
        petClothes.setName(dto.getName());
        petClothes.setNumber(dto.getNumber());
        petClothes.setPrice(new BigDecimal(dto.getPrice()));
        petClothes.setCost(new BigDecimal(dto.getCost()));
        petClothes.setFactory(dto.getFactory());
        petClothes.setRemake(dto.getRemake());
        petClothes.setProductionTime(dto.getProductionTime());
        petClothes.setShelfLife(dto.getShelfLife());
        petClothes.setDelStatus(DelStatusEnum.NORMAL.getCode());
        petClothes.setCreateTime(new Date());
        petClothes.setUpdateTime(new Date());
        petClothesMapper.insertSelective(petClothes);
        return 1;
    }

    @Override
    public PetClothes selectPetClothesById(Integer id) {
        PetClothes petClothes = petClothesMapper.selectByPrimaryKey(id);
        return petClothes;
    }

    @Override
    public int updatePetClothes(PetClothes petClothes) {
        petClothes.setUpdateTime(new Date());
        petClothesMapper.updateByPrimaryKeySelective(petClothes);
        return 1;
    }

    @Override
    public int deletePetClothesByIds(String ids) {
        List<PetClothes> infos = petClothesMapper.selectByIds(ids);
        for (PetClothes info : infos) {
            info.setDelStatus(DelStatusEnum.DELETE.getCode());
            info.setUpdateTime(new Date());
            petClothesMapper.updateByPrimaryKey(info);
        }
        return 1;
    }
}
