package com.punjuprogrammers.memberbook.bl.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(indexes={
		@Index(name="name",columnList="name",unique=true)
		})
@NamedQueries(
		@NamedQuery(name="MailTemplate.findByName",query="SELECT c FROM MailTemplate c WHERE c.name=:name")
		)
public class MailTemplate implements Serializable, IdObject<Long>{
	private static final long serialVersionUID = -52928630060835712L;

	@TableGenerator(name = "templateId", table = "id_gen", pkColumnName = "id_key", valueColumnName = "id_value", allocationSize = 1,initialValue=1000)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "templateId")	
	private long templateId;
	
	@Column(length=50)
	private String name;
	@Column(length=250)
	private String subject;
	@Lob
	@Column()
	private String text;
	@Column(length=100)
	private String fromEmail;
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="attachments",joinColumns=@JoinColumn(name="templateId"))
	private List<MailAttachment> attachments;

	public Long getId() {
		return templateId;
	}
	public void setId(Long id) {
		this.templateId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public List<MailAttachment> getAttachments() {
		if (attachments == null) {
			attachments = new ArrayList<MailAttachment>();
		}
		return attachments;
	}
	public void setAttachments(List<MailAttachment> attachments) {
		this.attachments = attachments;
	}
	@Override
	public String toString() {
		return "MailTemplate [templateId=" + templateId + ", name=" + name + ", subject=" + subject + ", text=" + text + ", fromEmail=" + fromEmail + ", attachments=" + attachments
				+ "]";
	}
	
	
}
