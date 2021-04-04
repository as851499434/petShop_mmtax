package com.mmtax.business.controller.rabbitmq;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmtax.business.domain.PayRequestData;
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
public class BatchPaymentVerifyListener {
    private static final Logger logger = LoggerFactory.getLogger(BatchPaymentVerifyListener.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    IPaymentService paymentService;
    /**
     * 校验处理
     * @param message
     */
    @RabbitListener(queues = "${batch.payment.verify.mq.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeNotifyMessage(@Payload byte[] message) {
        PayRequestData payRequestData = null;
        try {
            payRequestData = objectMapper.readValue(message, PayRequestData.class);
            logger.info("校验的消息为：{}", JSON.toJSONString(payRequestData));
            paymentService.payDataCheck(payRequestData);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            logger.info("消费者批量打款校验通知{}出现异常:",JSON.toJSONString(payRequestData),e);
        }
    }
}
