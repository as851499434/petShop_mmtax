package com.mmtax.business.tianjindto;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/19 3:35 下午
 */
public class TianJinNotifyDTO {
    /**
     *地址
     */
    private String host;
    /**
     *端口
     */
    private String port;
    /**
     *url
     */
    private String url;
    /**
     * 是否 http 请求
     * 1 是 0 否
     */
    private String is_https;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIs_https() {
        return is_https;
    }

    public void setIs_https(String is_https) {
        this.is_https = is_https;
    }

    @Override
    public String toString() {
        return "TianJinNotifyDTO{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", url='" + url + '\'' +
                ", is_https='" + is_https + '\'' +
                '}';
    }
}
