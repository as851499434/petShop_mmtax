package com.mmtax.common.utils.esign.domain.flowonestep;

/**
 * @description 附件信息
 * @author 宫清
 * @date 2019年11月19日 下午2:22:40
 * @since JDK1.7
 */
public class Attachment {

	//附件Id
	private String fileId;
	//附件名称
	private String attachmentName;
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	
	public Attachment(String fileId, String attachmentName) {
		this.fileId = fileId;
		this.attachmentName = attachmentName;
	}
	public Attachment() {
	}
	
	
}
