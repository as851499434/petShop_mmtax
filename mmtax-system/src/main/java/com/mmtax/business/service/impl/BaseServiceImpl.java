package com.mmtax.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.dto.DateQueryDTO;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务层基本实现 实现公共方法
 * @author Ljd
 * @date 2020/7/10
 */
public class BaseServiceImpl {
    /**
     * logger 子类可以直接调用，注意，不要再在子类中再定义logger，否则会引起变量混乱
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

    /**
     * 封装分页参数
     * 生成分页查询基础参数类
     * @param currentPage 当前页
     * @param pageSize 每页条数
     * @return 封装类
     */
    public QueryPage convertQueryPage(Integer currentPage, Integer pageSize) {
        QueryPage queryPage = new QueryPage();
        if (currentPage != null) {
            queryPage.setCurrentPage(currentPage);
        }
        if (pageSize != null) {
            queryPage.setPageSize(pageSize);
        }
        return queryPage;
    }

    /**
     * 封装查询起止时间
     * 根据传入的起始时间，结束时间值，返回 yyyy-MM-dd HH:mm:ss 格式字符串时间值。
     * 若起始时间，结束时间都为空则原值返回，
     * 若其中一个为空，另一个向前或向后推算12个月时间，
     * 若都不为空则返回格式化字符串
     * @param startDate 起始时间
     * @param endDate 结束时间
     * @return 封装结果
     */
    public DateQueryDTO getDateQueryDTOMatchNorm(String startDate, String endDate) {
        DateQueryDTO dto = new DateQueryDTO();
        if (StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)) {
            dto.setStartDate(startDate);
            dto.setEndDate(endDate);
            return dto;
        }
        String sd;
        String ed;
        if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
            sd =  DateUtil.format(DateUtil.beginOfDay(DateUtil.parse(startDate)), DatePattern.NORM_DATETIME_PATTERN);
            ed =  DateUtil.format(DateUtil.endOfDay(DateUtil.parse(endDate)), DatePattern.NORM_DATETIME_PATTERN);
        }else {
            if (StringUtils.isNotEmpty(startDate)) {
                sd =  DateUtil.format(DateUtil.beginOfDay(DateUtil.parse(startDate)),
                        DatePattern.NORM_DATETIME_PATTERN);
                ed =  DateUtil.format(DateUtil.endOfDay(DateUtil.offsetMonth(DateUtil.parse(startDate), 12)),
                        DatePattern.NORM_DATETIME_PATTERN);
            }else {
                sd =  DateUtil.format(DateUtil.beginOfDay(DateUtil.offsetMonth(DateUtil.parse(endDate), -12)),
                        DatePattern.NORM_DATETIME_PATTERN);
                ed =  DateUtil.format(DateUtil.endOfDay(DateUtil.parse(endDate)), DatePattern.NORM_DATETIME_PATTERN);
            }
         }
        dto.setStartDate(sd);
        dto.setEndDate(ed);
        return dto;
    }
}
