package com.jeecms.cms.entity.assist;

import java.util.Date;

public class CmsBallotRecord {
	private Integer recordId;
	private Date voteTime;
	private String voteIp;
	private String voteCookie;
	private String wxopenId;
	private CmsBallotItem item;
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Date getVoteTime() {
		return voteTime;
	}
	public void setVoteTime(Date voteTime) {
		this.voteTime = voteTime;
	}
	public String getVoteIp() {
		return voteIp;
	}
	public void setVoteIp(String voteIp) {
		this.voteIp = voteIp;
	}
	public String getVoteCookie() {
		return voteCookie;
	}
	public void setVoteCookie(String voteCookie) {
		this.voteCookie = voteCookie;
	}
	public String getWxopenId() {
		return wxopenId;
	}
	public void setWxopenId(String wxopenId) {
		this.wxopenId = wxopenId;
	}
	public CmsBallotItem getItem() {
		return item;
	}
	public void setItem(CmsBallotItem item) {
		this.item = item;
	}
	
	
}
