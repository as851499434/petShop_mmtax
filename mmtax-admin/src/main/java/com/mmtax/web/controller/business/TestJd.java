package com.mmtax.web.controller.business;

import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.URLConnection;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 测试类
 * @author Ljd
 * @date 2020/4/30
 */
public class TestJd {

    public static void main(String[] args) {
//        String url = "http://localhost:8099/merchant/payment/paymentTest";
//        String str1 = "{\"mid\":\"6\",\"am\":\"10\"}";
//        try {
//            String result = HttpUtils.sendPost(url,str1);
//            System.out.println("result:" + result);
//        }catch (Exception e){
//
//        }
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
    }
}


class  ThreadDemo extends Thread{

    @Override
    public void run() {
        String url = "http://localhost:8099/merchant/payment/paymentTest";
        String str1 = "{\"mid\":\"6\",\"am\":\"10\"}";
        try {
            String result = HttpUtils.sendPost(url,str1);
            System.out.println("result:" + result);
        }catch (Exception e){

        }
    }
}
