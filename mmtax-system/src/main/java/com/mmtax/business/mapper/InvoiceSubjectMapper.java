package com.mmtax.business.mapper;

import com.mmtax.business.domain.InvoiceSubject;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 发票科目 数据层
 * 
 * @author meimiao
 * @date 2020-08-24
 */
public interface InvoiceSubjectMapper extends MyMapper<InvoiceSubject>
{
    /**
     *
     * @param content
     * @return
     */
    Integer queryIdBycontent(@Param("content") String content);
}