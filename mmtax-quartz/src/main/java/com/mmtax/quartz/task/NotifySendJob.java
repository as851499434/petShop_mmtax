package com.mmtax.quartz.task;

import com.mmtax.business.domain.NotifySendAgain;
import com.mmtax.business.domain.NotifyWkyc;
import com.mmtax.business.mapper.NotifySendAgainMapper;
import com.mmtax.business.mapper.NotifyWkycMapper;
import com.mmtax.business.service.INotifySendAgainService;
import com.mmtax.business.service.INotifyWkycService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component("NotifySendJob")
@Slf4j
public class NotifySendJob {

    private static final Integer MAX_SEND_NUM = 8;

    @Autowired
    private INotifySendAgainService notifySendAgainService;
    @Autowired
    private NotifySendAgainMapper notifySendAgainMapper;
    @Autowired
    INotifyWkycService notifyWkycService;
    @Autowired
    NotifyWkycMapper notifyWkycMapper;

    public void sendNotify(){
        log.info("发送异步通知定时任务开始----------");
        try {
            NotifySendAgain notifySendAgain = new NotifySendAgain();
            notifySendAgain.setNotifyStatus(2);
            List<NotifySendAgain> notifySendAgains = notifySendAgainMapper.select(notifySendAgain);
            if (CollectionUtils.isEmpty(notifySendAgains)) {
                log.info("没有需要发送的异步通知，return");
            }
            notifySendAgains.forEach(one -> {
                log.info("开始发送异步通知，id:{}", one.getId());
                Double minutes = Math.pow(5, one.getFailNum());
                notifySendAgainService.sendNotify(one, minutes.intValue());
            });
        }catch(Exception e){
            log.error("NotifySendJob定时任务出错：",e);
        }
        log.info("发送异步通知定时任务结束----------");
    }

    public void sendWkycNotify(){
        log.info("发送悟空云创通知定时任务开始----------");
        try {
            List<NotifyWkyc> notifyWkycs = notifyWkycMapper.selectNeedSend();
            if (CollectionUtils.isEmpty(notifyWkycs)) {
                log.info("没有需要发送的悟空云创通知，return");
                return;
            }
            notifyWkycs.forEach(one -> {
                log.info("开始发送异步通知，id:{}", one.getId());
                Double minutes = Math.pow(5, one.getFailNum());
                notifyWkycService.sendNotifyWkyc(one, minutes.intValue());
            });
        }catch(Exception e){
            log.error("sendWkycNotify定时任务出错：",e);
        }
        log.info("发送悟空云创通知定时任务结束----------");
    }
}
