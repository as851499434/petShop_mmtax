package com.mmtax.business.controller.merchant;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.yunzbdto.YunZBInvoiceBillDTO;
import com.mmtax.business.yunzbdto.YunZBPicInfoDTO;
import com.mmtax.business.yunzbdto.YunZBQueryChannelDTO;
import com.mmtax.business.yunzbdto.YunZBQueryInvoiceDTO;
import com.mmtax.common.utils.yunzbutil.Response;
import com.mmtax.common.utils.yunzbutil.YunZBConstants;
import com.mmtax.common.utils.yunzbutil.YunZBUtil;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/17 13:32
 */
public class Test {

    private static OrderDetail orderDetail = new OrderDetail();
    public static void main(String[] args) {
        try {
            Test test = new Test();
            YunZBQueryInvoiceDTO yunZBQueryInvoiceDTO = new YunZBQueryInvoiceDTO();
            yunZBQueryInvoiceDTO.setBatchId("13523533");
            String result = test.test();
            yunZBQueryInvoiceDTO.setChannelNo(result);
            yunZBQueryInvoiceDTO.setSubMchId("81103672473");
            yunZBQueryInvoiceDTO.setMchId("82601429062");
            Response response = YunZBUtil.remoteInvoke(YunZBConstants.INVOICE, yunZBQueryInvoiceDTO,
                    "2F9C6B9848263634695390BF7EE9D76F");
            System.out.println("response:" + response.getRespData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String test() throws Exception {
        YunZBQueryChannelDTO queryChannel = new YunZBQueryChannelDTO();
        queryChannel.setMchId("82601429062");
        Response response = YunZBUtil.remoteInvoke(YunZBConstants.CHANNEL, queryChannel, "2F9C6B9848263634695390BF7EE9D76F");
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(response.getRespData());
        String result = jsonObject.getJSONArray("channels").getJSONObject(0).getString("channel_no");
        return result;
    }


    static class OrderDetail {
        private String task_uuid;
        private String task_no;
        private String social_no;
        private String mobile_no;
        private String card_no;
        private String salary_amount;
        private String order_status;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTask_uuid() {
            return task_uuid;
        }

        public void setTask_uuid(String task_uuid) {
            this.task_uuid = task_uuid;
        }

        public String getTask_no() {
            return task_no;
        }

        public void setTask_no(String task_no) {
            this.task_no = task_no;
        }

        public String getSocial_no() {
            return social_no;
        }

        public void setSocial_no(String social_no) {
            this.social_no = social_no;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getSalary_amount() {
            return salary_amount;
        }

        public void setSalary_amount(String salary_amount) {
            this.salary_amount = salary_amount;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        @Override
        public String toString() {
            return "OrderDetail{" +
                    "task_uuid='" + task_uuid + '\'' +
                    ", task_no='" + task_no + '\'' +
                    ", social_no='" + social_no + '\'' +
                    ", mobile_no='" + mobile_no + '\'' +
                    ", card_no='" + card_no + '\'' +
                    ", salary_amount='" + salary_amount + '\'' +
                    ", order_status='" + order_status + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


    public static String sendPost(String url, String param) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Length", param.length() + "");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 查询渠道号
     *
     * @param merchantNo 渠道商户编号
     * @return
     * @throws Exception
     */
    public static Object queryChannels(String merchantNo, String secretKey) {
        Object result = new Object();
        try {
            YunZBQueryChannelDTO queryChannel = new YunZBQueryChannelDTO();
            queryChannel.setMchId(merchantNo);
            Response response = YunZBUtil.remoteInvoke(YunZBConstants.CHANNEL, queryChannel, secretKey);
            JSONObject jsonObject = JSONObject.fromObject(response.getRespData());
            result = jsonObject.getJSONArray("channels").getJSONObject(0).get("channel_no");
        } catch (Exception e) {

        }
        return result;
    }


    public static JSONObject uploadImg(String str1, String str2, String str3) throws Exception {
        //上传图片信息到云众包渠道
        YunZBPicInfoDTO yunZBPicInfoDTO = new YunZBPicInfoDTO();
        yunZBPicInfoDTO.setIdCardBack(str1);
        yunZBPicInfoDTO.setIdCardFront(str2);
        yunZBPicInfoDTO.setLicence(str3);
        yunZBPicInfoDTO.setSubMchId("81103087843");
        yunZBPicInfoDTO.setReqTime(DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN));
        yunZBPicInfoDTO.setNonceStr(YunZBConstants.UUID);
        Response response = YunZBUtil.remoteInvoke(YunZBConstants.UPLOAD_PIC, yunZBPicInfoDTO,
                YunZBConstants.secretKey);
        JSONObject tokenResult =
                JSONObject.fromObject(response.getRespData());
        return tokenResult;
    }
}
