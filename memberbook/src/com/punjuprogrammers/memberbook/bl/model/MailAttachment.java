package com.punjuprogrammers.memberbook.bl.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class MailAttachment {

	@Column
	private String fileName;
	@Lob
	@Column
	private byte[] contents;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getContents() {
		return contents;
	}
	public void setContents(byte[] contents) {
		this.contents = contents;
	}
}
