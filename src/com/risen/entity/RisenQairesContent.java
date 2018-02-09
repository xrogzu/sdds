package com.risen.entity;

import com.jeecms.core.entity.CmsUser;


public class RisenQairesContent {
	private Integer id;
	private RisenQairesTopic topic;
	private CmsUser user;
	private String content;
	private RisenQairesRecord record;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public RisenQairesTopic getTopic() {
		return topic;
	}
	public void setTopic(RisenQairesTopic topic) {
		this.topic = topic;
	}
	public CmsUser getUser() {
		return user;
	}
	public void setUser(CmsUser user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public RisenQairesRecord getRecord() {
		return record;
	}
	public void setRecord(RisenQairesRecord record) {
		this.record = record;
	}
	
}
