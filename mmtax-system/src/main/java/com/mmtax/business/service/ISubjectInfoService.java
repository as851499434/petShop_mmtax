package com.mmtax.business.service;

import com.mmtax.business.domain.SubjectInfo;

import java.util.List;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/20 3:57 下午
 */
public interface ISubjectInfoService {
    /**
     * 获取所有的代征主体信息
     * @return
     */
    List<SubjectInfo> getAll();
}
