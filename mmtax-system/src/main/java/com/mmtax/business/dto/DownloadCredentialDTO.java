package com.mmtax.business.dto;

/**
 * @Author：YH
 * @Date：2020/9/27 15:43
 */
public class DownloadCredentialDTO {

    private String createTime;

    private String Pid;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    @Override
    public String toString() {
        return "DownloadCredential{" +
                "createTime='" + createTime + '\'' +
                ", Pid='" + Pid + '\'' +
                '}';
    }
}
