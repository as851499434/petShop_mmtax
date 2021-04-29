package com.mmtax.business.service.impl;

import com.mmtax.business.domain.PetServiceRecord;
import com.mmtax.business.domain.PetToys;
import com.mmtax.business.dto.AddPetToyDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.business.mapper.PetInfoMapper;
import com.mmtax.business.mapper.PetMasterInfoMapper;
import com.mmtax.business.mapper.PetServiceRecordMapper;
import com.mmtax.business.mapper.PetToysMapper;
import com.mmtax.common.enums.DelStatusEnum;
import com.mmtax.common.exception.BusinessException;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPetToysService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 宠物玩具 服务层实现
 * 
 * @author meimiao
 * @date 2021-04-29
 */
@Service
public class PetToysServiceImpl implements IPetToysService
{
    @Resource
    PetToysMapper petToysMapper;
    @Resource
    PetInfoMapper petInfoMapper;
    @Resource
    PetMasterInfoMapper petMasterInfoMapper;
    @Override
    public List<PetToys> selectPetToysList(PetGoodQueryDTO dto) {
        List<PetToys> petToys = petToysMapper.selectPetToysList(dto);
        return petToys;
    }

    @Override
    public int insertPetToys(AddPetToyDTO dto) {
        PetToys petToys = new PetToys();
        petToys.setName(dto.getName());
        petToys.setNumber(dto.getNumber());
        petToys.setPrice(new BigDecimal(dto.getPrice()));
        petToys.setCost(new BigDecimal(dto.getCost()));
        petToys.setFactory(dto.getFactory());
        petToys.setRemake(dto.getRemake());
        petToys.setProductionTime(dto.getProductionTime());
        petToys.setDelStatus(DelStatusEnum.NORMAL.getCode());
        petToys.setCreateTime(new Date());
        petToys.setUpdateTime(new Date());
        petToysMapper.insertSelective(petToys);
        return 1;
    }

    @Override
    public PetToys selectPetToysById(Integer id) {
        PetToys petToys = petToysMapper.selectByPrimaryKey(id);
        return petToys;
    }

    @Override
    public int updatePetToys(PetToys petToys) {
        petToys.setUpdateTime(new Date());
        petToysMapper.updateByPrimaryKeySelective(petToys);
        return 1;
    }

    @Override
    public int deletePetToysByIds(String ids) {
        List<PetToys> infos = petToysMapper.selectByIds(ids);
        for (PetToys info : infos) {
            info.setDelStatus(DelStatusEnum.DELETE.getCode());
            info.setUpdateTime(new Date());
            petToysMapper.updateByPrimaryKey(info);
        }
        return 1;
    }
}
