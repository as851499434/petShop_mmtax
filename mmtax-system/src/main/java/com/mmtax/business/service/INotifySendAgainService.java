package com.mmtax.business.service;

import com.mmtax.business.domain.CustomerProtocol;
import com.mmtax.business.domain.NotifySendAgain;
import java.util.List;

/**
 * 异步通知重发送记录 服务层
 * 
 * @author meimiao
 * @date 2020-06-01
 */
public interface INotifySendAgainService
{
    /**
     * 添加通知记录
     */
    int insertNotifyLog(NotifySendAgain notifySendAgain);

    /**
     * 发送通知
     */
    void sendNotify(NotifySendAgain notifySendAgain,Integer minutes);

    /**
     * 签约结果发送异步通知
     */
    void sendSignNotify(CustomerProtocol customerProtocol);
}
