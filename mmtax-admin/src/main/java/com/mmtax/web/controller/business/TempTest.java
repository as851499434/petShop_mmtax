package com.mmtax.web.controller.business;

import com.mmtax.MmtaxApplicationTests;
import com.mmtax.business.service.IMerchantAccountService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * 测试类
 * @author Ljd
 * @date 2020/4/29
 */
public class TempTest extends MmtaxApplicationTests {

    protected final Logger logger = LoggerFactory.getLogger(TempTest.class);

    @Autowired
    private IMerchantAccountService merchantAccountService;

    @Test
    public void test() {
        merchantAccountService.updateMerchantAccountByVersion(5, BigDecimal.valueOf(10));
    }

    @Test
    public void test1() {
        merchantAccountService.updateMerchantAccountByVersion(5, BigDecimal.valueOf(10));
    }

    @Test
    public void testAccount() {
        int num = 2;
        for (int i = 0; i < num; i++) {
            if (i % 2 == 0) {
                ThreadDemo threadDemo = new ThreadDemo(BigDecimal.valueOf(10));
                threadDemo.start();
                logger.info("thread name: " + Thread.currentThread().getName() + ",i:" + i);
            }else {
                ThreadDemo threadDemo = new ThreadDemo(BigDecimal.valueOf(-10));
                threadDemo.start();
                logger.info("thread name: " + Thread.currentThread().getName() + ",i:" + i);
            }
        }
    }

    class  ThreadDemo extends Thread{
        private BigDecimal am;

        public ThreadDemo(BigDecimal am) {
            this.am = am;
        }
        @Override
        public void run() {
            Integer mid = 5;
            merchantAccountService.updateMerchantAccountByVersion(mid, am);
        }
    }





}
