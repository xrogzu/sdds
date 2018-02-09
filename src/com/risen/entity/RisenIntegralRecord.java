package com.risen.entity;

import java.util.Date;

public class RisenIntegralRecord {
	/**
	 *  RISENIR_ORGID NUMBER NOT NULL,
       RISENIR_ORGNAME VARCHAR(50),
       RISENIR_CHANNEL VARCHAR(50),
       RISENIR_HANDLEDATE DATE,
       RISENIR_SCORE NUMBER
       RISENIR_TARGETORGID 目标组织编号
       RISENIR_CONTENTID 共享内容编号
       RISENIR_CONTENT 共享内容
       RISENIR_RESULT 共享结果 0 不采纳 1 采纳
	 */
	private Integer risenirUuid;//编号
	private Integer risenirOrgid;//组织编号
	private String risenirOrgname;//组织名称
	private String risenirChannel;
	private Date risenirHandledate;
	private Double risenirScore;//分数
	private Integer risenirTargetorgid;//目标组织编号
	private Integer risenirContentid;//共享内容编号
	private String risenirContent;//共享内容
	private Integer risenirResult;//共享结果 0 不采纳 1 采纳
	private String risenirContenturl;//共享内容路径
	private Integer risenirSharecheck;//共享检查的编号 
	private String risenirTargetchannel;//目标栏目名
	private String stuats;//状态
	private String risenirContenttype;//文章类型
	
	public String getRisenirContenttype() {
		return risenirContenttype;
	}
	public void setRisenirContenttype(String risenirContenttype) {
		this.risenirContenttype = risenirContenttype;
	}
	public String getStuats() {
		return stuats;
	}
	public void setStuats(String stuats) {
		this.stuats = stuats;
	}
	public String getRisenirTargetchannel() {
		return risenirTargetchannel;
	}
	public void setRisenirTargetchannel(String risenirTargetchannel) {
		this.risenirTargetchannel = risenirTargetchannel;
	}
	public Integer getRisenirSharecheck() {
		return risenirSharecheck;
	}
	public void setRisenirSharecheck(Integer risenirSharecheck) {
		this.risenirSharecheck = risenirSharecheck;
	}
	public String getRisenirContenturl() {
		return risenirContenturl;
	}
	public void setRisenirContenturl(String risenirContenturl) {
		this.risenirContenturl = risenirContenturl;
	}
	public Integer getRisenirTargetorgid() {
		return risenirTargetorgid;
	}
	public void setRisenirTargetorgid(Integer risenirTargetorgid) {
		this.risenirTargetorgid = risenirTargetorgid;
	}
	public Integer getRisenirContentid() {
		return risenirContentid;
	}
	public void setRisenirContentid(Integer risenirContentid) {
		this.risenirContentid = risenirContentid;
	}
	public String getRisenirContent() {
		return risenirContent;
	}
	public void setRisenirContent(String risenirContent) {
		this.risenirContent = risenirContent;
	}
	public Integer getRisenirResult() {
		return risenirResult;
	}
	public void setRisenirResult(Integer risenirResult) {
		this.risenirResult = risenirResult;
	}
	public Integer getRisenirUuid() {
		return risenirUuid;
	}
	public void setRisenirUuid(Integer risenirUuid) {
		this.risenirUuid = risenirUuid;
	}
	public Integer getRisenirOrgid() {
		return risenirOrgid;
	}
	public void setRisenirOrgid(Integer risenirOrgid) {
		this.risenirOrgid = risenirOrgid;
	}
	public String getRisenirOrgname() {
		return risenirOrgname;
	}
	public void setRisenirOrgname(String risenirOrgname) {
		this.risenirOrgname = risenirOrgname;
	}
	
	public String getRisenirChannel() {
		return risenirChannel;
	}
	public void setRisenirChannel(String risenirChannel) {
		this.risenirChannel = risenirChannel;
	}
	public Date getRisenirHandledate() {
		return risenirHandledate;
	}
	public void setRisenirHandledate(Date risenirHandledate) {
		this.risenirHandledate = risenirHandledate;
	}
	public Double getRisenirScore() {
		return risenirScore;
	}
	public void setRisenirScore(Double risenirScore) {
		this.risenirScore = risenirScore;
	}
	
	
	
}
