package com.mmtax.business.service.impl;


import com.mmtax.business.domain.CommonProblem;
import com.mmtax.business.mapper.CommonProblemMapper;
import com.mmtax.business.service.ICommonProblemService;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.enums.DelStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 常见问题 服务层实现
 * 
 * @author meimiao
 * @date 2020-11-27
 */
@Service
public class CommonProblemServiceImpl implements ICommonProblemService
{
    @Resource
    CommonProblemMapper commonProblemMapper;

    private Integer maxLength = 500;

    @Override
    public List<CommonProblem> selectCommonProblemList(String problem, String startDate, String endDate) {
        Example example = new Example(CommonProblem.class);
        Example.Criteria cr = example.createCriteria();
        example.setOrderByClause("create_time desc");
        if(StringUtils.isNotEmpty(problem)) {
            cr.andLike("problem", "%" + problem + "%");
        }
        cr.andEqualTo("delStatus", DelStatusEnum.NORMAL.getCode());
        if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
            cr.andBetween("createTime",startDate+" 00:00:00",endDate +" 23:59:59");
        }
        List<CommonProblem> commonProblems = commonProblemMapper.selectByExample(example);
        return commonProblems;
    }

    @Override
    public Boolean insertCommonProblem(CommonProblem commonProblem) {
        commonProblem.setDelStatus(DelStatusEnum.NORMAL.getCode());
        commonProblem.setProblemNo(ChanPayUtil.generateOutTradeNo());
        commonProblem.setProblemNo(ProblemNo());
        error(commonProblem);
        commonProblemMapper.insertSelective(commonProblem);
        return true;
    }

    @Override
    public Boolean deleteCommonProblemByIds(String ids) {
        List<CommonProblem> commonProblems = commonProblemMapper.selectByIds(ids);
        for (CommonProblem commonProblem : commonProblems) {
            commonProblem.setDelStatus(DelStatusEnum.DELETE.getCode());
            commonProblemMapper.updateByPrimaryKeySelective(commonProblem);
        }
        return true;
    }

    @Override
    public CommonProblem selectCommonProblemById(Integer id) {
        CommonProblem commonProblem = new CommonProblem();
        commonProblem.setId(id);
        CommonProblem problem = commonProblemMapper.selectOne(commonProblem);
        return problem;
    }

    @Override
    public Boolean updateCommonProblem(CommonProblem commonProblem) {
        error(commonProblem);
        commonProblemMapper.updateByPrimaryKeySelective(commonProblem);
        return true;
    }

    /**
     * 条件限制
     * @param commonProblem
     */
    private void error(CommonProblem commonProblem) {
        if (StringUtils.isEmpty(commonProblem.getProblem())) {
            throw new BusinessException("问题不可为空");
        }
        if (StringUtils.isEmpty(commonProblem.getAnswer())) {
            throw new BusinessException("解答不可为空");
        }
        if (commonProblem.getAnswer().length() > maxLength) {
            throw new BusinessException("解答超过长度限制");
        }
    }

    /**
     * 随机编号方法
     * @return
     */
    private String ProblemNo(){
        String random =
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                        String.valueOf(new Double(Math.round(Math.random() * 1000)).longValue());
        int c='A'+(int)(Math.random()*26);
        String no =(char) c  + random;
        return no;
    }
}
