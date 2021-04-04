package com.mmtax.quartz.task;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.BatchPaymentRecord;
import com.mmtax.business.mapper.BatchPaymentRecordMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.common.enums.BatchPaymentRecordStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("HandBatchPaymentRecordJob")
@Slf4j
public class HandBatchPaymentRecordJob {

    @Autowired
    BatchPaymentRecordMapper batchPaymentRecordMapper;
    @Autowired
    TrxOrderMapper trxOrderMapper;

    public void handStatus() {
        BatchPaymentRecord query = new BatchPaymentRecord();
        query.setStatus(BatchPaymentRecordStatusEnum.GOING.code);
        List<BatchPaymentRecord> recordList = batchPaymentRecordMapper.select(query);
        recordList.forEach(one->{
            if(one.getPaymentCount().equals(trxOrderMapper.countTrxOrderFinish(one.getId()))){
                one.setStatus(BatchPaymentRecordStatusEnum.COMPLETE.code);
                one.setUpdateTime(DateUtil.date());
                batchPaymentRecordMapper.updateByPrimaryKeySelective(one);
            }
        });
    }
}
