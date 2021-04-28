package com.mmtax.business.mapper;

import com.mmtax.business.domain.PetServiceRecord;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.common.utils.MyMapper;

import java.util.List;

/**
 * 宠物服务记录 数据层
 * 
 * @author meimiao
 * @date 2021-04-28
 */
public interface PetServiceRecordMapper extends MyMapper<PetServiceRecord>
{
    List<PetInfoDTO> selectPetServiceRecordList(PetInfoQueryDTO dto);
}