package com.mmtax.business.mapper;

import com.mmtax.business.domain.PetFosterRecord;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.common.utils.MyMapper;

import java.util.List;

/**
 * 宠物寄养记录 数据层
 * 
 * @author meimiao
 * @date 2021-04-27
 */
public interface PetFosterRecordMapper extends MyMapper<PetFosterRecord>
{
    List<PetInfoDTO> selectPetFosterRecordList(PetInfoQueryDTO dto);
}