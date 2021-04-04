/**
 * 
 */
package com.mmtax.common.utils.esign.helper;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @author chen_xi
 * 验证签名
 */
@Slf4j
public class SafeVerify {
	
	public static boolean checkPass(HttpServletRequest request, String appSecret,String reqContent) {
		String signture =  request.getHeader("X-Tsign-Open-SIGNATURE");
		//1. 获取时间戳的字节流
		String timestamp = request.getHeader("X-Tsign-Open-TIMESTAMP");
//		String content_type  =request
    	//2. 获取query请求字符串
		String requestQuery = getRequestQueryStr(request);
		//3. 获取body的数据
//		String rbody = getRequestBody(request,"UTF-8");
		//4、按照规则进行加密
		String signdata = timestamp + requestQuery + reqContent;
		log.info("signdata={}",signdata);
        String mySignature= DigestHelper.getSignature(signdata, appSecret,"HmacSHA256","UTF-8");
        log.info("加密出来的签名值：----------->>>>>>"+mySignature);
        log.info("header里面的签名值：---------->>>>>>"+signture);
        if(mySignature.equals(signture)) {
        	log.info("校验通过");
        	return true;
        }else {
        	log.info("校验失败");
        	return  false;
        }
		
	}
	/**
	 * 获取请求body
	 * @param request
	 * @param encoding
	 * @return
	 */
	  private static String getRequestBody(HttpServletRequest request, String encoding) {
		    // 请求内容RequestBody
		    String reqBody = null;
		    int contentLength = request.getContentLength();
		    if (contentLength < 0) {
		      return null;
		    }
		    byte buffer[] = new byte[contentLength];
		    try {
		      for (int i = 0; i < contentLength;) {
		        int readlen = request.getInputStream().read(buffer, i, contentLength - i);
		        if (readlen == -1) {
		          break;
		        }
		        i += readlen;
		      }
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    try {
		      reqBody = new String(buffer, encoding);
		    } catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
		    }
		    return reqBody;
		  }
	  /**
	   * 获取query请求字符串
	   * @param request
	   * @return
	   */
	   private static String getRequestQueryStr(HttpServletRequest request) {
		   //对 Query 参数按照字典对 Key 进行排序后,按照value1+value2方法拼接
			//转换一下数据类型并排序
			List<String> req_List= new ArrayList();
			Enumeration<String> reqEnu =request.getParameterNames();
			 while (reqEnu.hasMoreElements()){
				 req_List.add(reqEnu.nextElement());
		      }
			Collections.sort(req_List);
			String requestQuery = "";
			for (String key : req_List) {
				String value = request.getParameter(key);
			     requestQuery += value == null ? "" : value;
			}
			log.info("获取的query请求字符串是：------》》》"+requestQuery);
			return  requestQuery;
	   }
	
}
