package com.mmtax.business.tianjindto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/26 2:49 下午
 */
public class TianJinServerInfoDTO {
    /**
     *供应商uuid
     */
    @ApiModelProperty("供应商uuid")
    private String server_uuid;
    /**
     *供应商名称
     */
    @ApiModelProperty("供应商名称")
    private String server_name;
    /**
     *服务协议uuid
     */
    @ApiModelProperty("服务协议uuid")
    private String contract_uuid;

    public String getServer_uuid() {
        return server_uuid;
    }

    public void setServer_uuid(String server_uuid) {
        this.server_uuid = server_uuid;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public String getContract_uuid() {
        return contract_uuid;
    }

    public void setContract_uuid(String contract_uuid) {
        this.contract_uuid = contract_uuid;
    }

    @Override
    public String toString() {
        return "TianJinServerInfoDTO{" +
                "server_uuid='" + server_uuid + '\'' +
                ", server_name='" + server_name + '\'' +
                ", contract_uuid='" + contract_uuid + '\'' +
                '}';
    }
}
