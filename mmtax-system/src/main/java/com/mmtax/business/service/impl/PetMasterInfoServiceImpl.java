package com.mmtax.business.service.impl;

import com.mmtax.business.domain.CommonProblem;
import com.mmtax.business.domain.PetMasterInfo;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.business.mapper.PetMasterInfoMapper;
import com.mmtax.common.enums.DelStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPetMasterInfoService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 宠物主人 服务层实现
 * 
 * @author meimiao
 * @date 2021-04-10
 */
@Service
public class PetMasterInfoServiceImpl implements IPetMasterInfoService
{
    @Resource
    PetMasterInfoMapper petMasterInfoMapper;

    @Override
    public List<PetMasterInfo> selectPetMasterInfoList(PetInfoQueryDTO queryDTO) {
        Example example = new Example(PetMasterInfo.class);
        Example.Criteria cr = example.createCriteria();
        example.setOrderByClause("create_time desc");
        if(StringUtils.isNotEmpty(queryDTO.getName())) {
            cr.andLike("name", "%" + queryDTO.getName() + "%");
        }
        if(StringUtils.isNotEmpty(queryDTO.getPhonenumber())) {
            cr.andLike("phonenumber", "%" + queryDTO.getPhonenumber() + "%");
        }
        if(StringUtils.isNotEmpty(queryDTO.getEmail())) {
            cr.andLike("email", "%" + queryDTO.getEmail() + "%");
        }
        cr.andEqualTo("delStatus", DelStatusEnum.NORMAL.getCode());
        if (StringUtils.isNotEmpty(queryDTO.getStartDate()) && StringUtils.isNotEmpty(queryDTO.getEndDate())) {
            cr.andBetween("createTime",queryDTO.getStartDate()+
                    " 00:00:00",queryDTO.getEndDate() +" 23:59:59");
        }
        List<PetMasterInfo> infos = petMasterInfoMapper.selectByExample(example);
        return infos;
    }

    @Override
    public int insertPetMasterInfo(PetMasterInfo petMasterInfo) {
        PetMasterInfo info = new PetMasterInfo();
        info.setPhonenumber(petMasterInfo.getPhonenumber());
        info.setDelStatus(DelStatusEnum.NORMAL.getCode());
        List<PetMasterInfo> infos = petMasterInfoMapper.select(info);
        if (infos.size() > 0) {
            throw new BusinessException("手机号已被注册");
        }
        petMasterInfo.setDelStatus(DelStatusEnum.NORMAL.getCode());
        petMasterInfo.setCreateTime(new Date());
        petMasterInfo.setUpdateTime(new Date());
        petMasterInfoMapper.insertSelective(petMasterInfo);
        return 1;
    }

    @Override
    public PetMasterInfo selectPetMasterInfoById(Integer id) {
        PetMasterInfo petMasterInfo = petMasterInfoMapper.selectByPrimaryKey(id);
        return petMasterInfo;
    }

    @Override
    public int updatePetMasterInfo(PetMasterInfo petMasterInfo) {
        PetMasterInfo info = petMasterInfoMapper.selectByPrimaryKey(petMasterInfo);
        info.setUpdateTime(new Date());
        petMasterInfoMapper.updateByPrimaryKeySelective(petMasterInfo);
        return 1;
    }

    @Override
    public int deletePetMasterInfoByIds(String ids) {
        List<PetMasterInfo> infos = petMasterInfoMapper.selectByIds(ids);
        for (PetMasterInfo info : infos) {
            info.setDelStatus(DelStatusEnum.DELETE.getCode());
            info.setUpdateTime(new Date());
            petMasterInfoMapper.updateByPrimaryKey(info);
        }
        return 1;
    }
}
