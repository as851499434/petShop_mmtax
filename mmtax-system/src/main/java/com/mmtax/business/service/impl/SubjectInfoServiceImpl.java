package com.mmtax.business.service.impl;

import com.mmtax.business.domain.SubjectInfo;
import com.mmtax.business.mapper.SubjectInfoMapper;
import com.mmtax.business.service.ISubjectInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/20 3:58 下午
 */
@Service
public class SubjectInfoServiceImpl implements ISubjectInfoService {

    @Resource
    SubjectInfoMapper subjectInfoMapper;

    @Override
    public List<SubjectInfo> getAll() {
        return subjectInfoMapper.selectAll();
    }
}
