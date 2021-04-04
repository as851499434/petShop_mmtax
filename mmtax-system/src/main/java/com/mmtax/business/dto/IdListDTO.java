package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 通用类 用于接收各种id集合等
 *
 * @author yuanligang
 * @date 2019/8/5
 */
public class IdListDTO {
    @ApiModelProperty(value = "ID集合", required = true)
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "FlowListDTO{" +
                "ids=" + ids +
                '}';
    }
}
