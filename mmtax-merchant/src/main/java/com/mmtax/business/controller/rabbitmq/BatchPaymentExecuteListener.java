package com.mmtax.business.controller.rabbitmq;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmtax.business.dto.BatchOrderDTO;
import com.mmtax.business.dto.PaymentInfoDTO;
import com.mmtax.business.service.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * 批量打款校验 监听
 * @author Ljd
 * @date 2020/11/3
 */
@Component
public class BatchPaymentExecuteListener {
    private static final Logger logger = LoggerFactory.getLogger(BatchPaymentExecuteListener.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    IPaymentService paymentService;
    /**
     * 校验处理
     * @param message
     */
    @RabbitListener(queues = "${batch.payment.Execute.mq.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeNotifyMessage(@Payload byte[] message) {
        logger.info("进入 consumeNotifyMessage,time:{}", DateUtil.format(DateUtil.date(),
                DatePattern.NORM_DATETIME_PATTERN));
        PaymentInfoDTO paymentInfoDTO = null;
        try {
            JSONObject jsonObject = objectMapper.readValue(message, JSONObject.class);
            logger.info("message：{}", jsonObject);
            paymentInfoDTO = jsonObject.toJavaObject(PaymentInfoDTO.class);
            BatchOrderDTO dto = new BatchOrderDTO();
            dto.setPaymentInfo(paymentInfoDTO);
            dto.setMerchantId(paymentInfoDTO.getMerchantId());
            dto.setBatchNo(paymentInfoDTO.getBatchNo());
            paymentService.batchPayment(dto);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.info("打款消费者消费消息异常：{},异常信息:{}", JSON.toJSONString(paymentInfoDTO),e);
        }
    }
}
