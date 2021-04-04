package com.mmtax.common.utils.esign.domain.flowonestep;

/**
 * @description 文档信息
 * @author 宫清
 * @date 2019年11月19日 下午2:28:27
 * @since JDK1.7
 */
public class Doc {
	//文档Id
	private String fileId;
	
	//文档名称
	private String fileName;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Doc(String fileId, String fileName) {
		this.fileId = fileId;
		this.fileName = fileName;
	}

	public Doc() {
	}
	
	
}
