package com.mmtax.business.service;

import com.mmtax.business.domain.CommonProblem;

import java.util.List;

/**
 * 常见问题 服务层
 * 
 * @author meimiao
 * @date 2020-11-27
 */
public interface ICommonProblemService
{
    /**
     * 查询常见问题列表
     * @param problem
     * @param startDate
     * @param endDate
     * @return
     */
    List<CommonProblem> selectCommonProblemList(String problem,String startDate,String endDate);

    /**
     * 新增保存常见问题
     * @param commonProblem
     * @return
     */
    Boolean insertCommonProblem(CommonProblem commonProblem);

    /**
     * 删除常见问题
     * @param ids
     * @return
     */
    Boolean deleteCommonProblemByIds(String ids);

    /**
     * 通过id查询常见问题信息
     * @param id
     * @return
     */
    CommonProblem selectCommonProblemById(Integer id);

    /**
     * 修改保存常见问题
     * @param commonProblem
     * @return
     */
    Boolean updateCommonProblem(CommonProblem commonProblem);
}
