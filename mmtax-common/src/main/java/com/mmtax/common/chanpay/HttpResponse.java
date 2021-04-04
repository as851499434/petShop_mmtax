package com.mmtax.common.chanpay;

import org.apache.commons.httpclient.Header;

import java.io.UnsupportedEncodingException;

/**
 * 
 * @author ocean
 *
 */
public class HttpResponse {
	
	public static String input_charset = "utf-8";

    /**
     * 返回中的Header信息
     */
    private Header[] responseHeaders;

    /**
     * String类型的result
     */
    private String   stringResult;

    /**
     * btye类型的result
     */
    private byte[]   byteResult;

    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public byte[] getByteResult() {
        if (byteResult != null) {
            return byteResult;
        }
        if (stringResult != null) {
            return stringResult.getBytes();
        }
        return null;
    }

    public void setByteResult(byte[] byteResult) {
        this.byteResult = byteResult;
    }

    public String getStringResult() throws UnsupportedEncodingException {
        if (stringResult != null) {
            return stringResult;
        }
        if (byteResult != null) {
            return new String(byteResult, input_charset);
        }
        return null;
    }

    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

}
