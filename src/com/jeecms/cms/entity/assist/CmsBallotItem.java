package com.jeecms.cms.entity.assist;

import java.util.Set;

public class CmsBallotItem {
	private Integer itemId;
	private Integer voteCount;
	private String picture;
	private String itemUrl;
	private String describtion;
	private String item;
	private CmsBallot ballot;
	private Set<CmsBallotRecord> record;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}
	public Set<CmsBallotRecord> getRecord() {
		return record;
	}
	public void setRecord(Set<CmsBallotRecord> record) {
		this.record = record;
	}
	public CmsBallot getBallot() {
		return ballot;
	}
	public void setBallot(CmsBallot ballot) {
		this.ballot = ballot;
	}
	
}
