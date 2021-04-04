package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/14 14:39
 */
@ApiModel(description = "添加反馈信息")
public class MerchantSaveOrderSheetFeedBackDTO {

    @ApiModelProperty(value = "记录id")
    private Integer id;
    @ApiModelProperty(value = "附件链接")
    private String fileName;
    @ApiModelProperty(value = "反馈说明")
    private String feedBackComment;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFeedBackComment() {
        return feedBackComment;
    }

    public void setFeedBackComment(String feedBackComment) {
        this.feedBackComment = feedBackComment;
    }
}
