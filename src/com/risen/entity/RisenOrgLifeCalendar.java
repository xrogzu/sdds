package com.risen.entity;

import java.util.Date;

public class RisenOrgLifeCalendar {
	private Integer risenlcUuid;//编号
	private Integer risenlcOrgid;//所属组织编号
	private String risenlcOrgname;//所属组织名称
	private Integer risenlcHolderid;//举办人编号
	private String risenlcHoldername;//举办人姓名
	private String risenlcMeetingtype;//1党员大会、2党小组会议、3党课会议、4党支部委员会、5党支部组织生活会
	private String risenlcAddress;//活动地点
	private String risenlcContactor;//联系人
	private String risenlcContent;//联系人电话
	private String risenlcParticipant;//参与人 多个逗号隔开
	private Integer risenlcExpectdate;//预计所需时间
	private Date risenlcReminddate;//提醒时间
	private String risenlcComment;//描述
	private Date risenlcOdate;//举办时间
	private String risenlcRemark;//缺席名单及原因
	private String risenlcZuzhiname;//组织名称
	
	private String risenlcYdrs;
	private String risenlcSdrs;
	private String risenlcLd1;
	private String risenlcLd2;
	private String risenlcLd3;
	private String risenlcLd4;
	private String risenlcLd5;
	private String risenlcLd6;
	private String risenlcSm1;
	private String risenlcSm2;
	private String risenlcSm3;
	private String risenlcSm4;
	private String risenlcSm5;
	private String risenlcSm6;
	private String risenlcYn1;
	private String risenlcYn2;
	private String risenlcYn3;
	private String risenlcYn4;
	private String risenlcYn5;
	private String risenlcYn6;
	
	

	public String getRisenlcZuzhiname() {
		return risenlcZuzhiname;
	}
	public void setRisenlcZuzhiname(String risenlcZuzhiname) {
		this.risenlcZuzhiname = risenlcZuzhiname;
	}
	public String getRisenlcYn1() {
		return risenlcYn1;
	}
	public void setRisenlcYn1(String risenlcYn1) {
		this.risenlcYn1 = risenlcYn1;
	}
	public String getRisenlcYn2() {
		return risenlcYn2;
	}
	public void setRisenlcYn2(String risenlcYn2) {
		this.risenlcYn2 = risenlcYn2;
	}
	public String getRisenlcYn3() {
		return risenlcYn3;
	}
	public void setRisenlcYn3(String risenlcYn3) {
		this.risenlcYn3 = risenlcYn3;
	}
	public String getRisenlcYn4() {
		return risenlcYn4;
	}
	public void setRisenlcYn4(String risenlcYn4) {
		this.risenlcYn4 = risenlcYn4;
	}
	public String getRisenlcYn5() {
		return risenlcYn5;
	}
	public void setRisenlcYn5(String risenlcYn5) {
		this.risenlcYn5 = risenlcYn5;
	}
	public String getRisenlcYn6() {
		return risenlcYn6;
	}
	public void setRisenlcYn6(String risenlcYn6) {
		this.risenlcYn6 = risenlcYn6;
	}
	public String getRisenlcYdrs() {
		return risenlcYdrs;
	}
	public void setRisenlcYdrs(String risenlcYdrs) {
		this.risenlcYdrs = risenlcYdrs;
	}
	public String getRisenlcSdrs() {
		return risenlcSdrs;
	}
	public void setRisenlcSdrs(String risenlcSdrs) {
		this.risenlcSdrs = risenlcSdrs;
	}
	public String getRisenlcLd1() {
		return risenlcLd1;
	}
	public void setRisenlcLd1(String risenlcLd1) {
		this.risenlcLd1 = risenlcLd1;
	}
	public String getRisenlcLd2() {
		return risenlcLd2;
	}
	public void setRisenlcLd2(String risenlcLd2) {
		this.risenlcLd2 = risenlcLd2;
	}
	public String getRisenlcLd3() {
		return risenlcLd3;
	}
	public void setRisenlcLd3(String risenlcLd3) {
		this.risenlcLd3 = risenlcLd3;
	}
	public String getRisenlcLd4() {
		return risenlcLd4;
	}
	public void setRisenlcLd4(String risenlcLd4) {
		this.risenlcLd4 = risenlcLd4;
	}
	public String getRisenlcLd5() {
		return risenlcLd5;
	}
	public void setRisenlcLd5(String risenlcLd5) {
		this.risenlcLd5 = risenlcLd5;
	}
	public String getRisenlcLd6() {
		return risenlcLd6;
	}
	public void setRisenlcLd6(String risenlcLd6) {
		this.risenlcLd6 = risenlcLd6;
	}
	public String getRisenlcSm1() {
		return risenlcSm1;
	}
	public void setRisenlcSm1(String risenlcSm1) {
		this.risenlcSm1 = risenlcSm1;
	}
	public String getRisenlcSm2() {
		return risenlcSm2;
	}
	public void setRisenlcSm2(String risenlcSm2) {
		this.risenlcSm2 = risenlcSm2;
	}
	public String getRisenlcSm3() {
		return risenlcSm3;
	}
	public void setRisenlcSm3(String risenlcSm3) {
		this.risenlcSm3 = risenlcSm3;
	}
	public String getRisenlcSm4() {
		return risenlcSm4;
	}
	public void setRisenlcSm4(String risenlcSm4) {
		this.risenlcSm4 = risenlcSm4;
	}
	public String getRisenlcSm5() {
		return risenlcSm5;
	}
	public void setRisenlcSm5(String risenlcSm5) {
		this.risenlcSm5 = risenlcSm5;
	}
	public String getRisenlcSm6() {
		return risenlcSm6;
	}
	public void setRisenlcSm6(String risenlcSm6) {
		this.risenlcSm6 = risenlcSm6;
	}
	/**
	 * 这4个字段用于条件查询
	 */
	private Date startDate;//活动时间起始日期
	private Date endDate;//活动时间结束日期
	private Date startDate1;//提醒时间起始日期
	private Date endDate1;//提醒时间结束日期
	public Integer getRisenlcUuid() {
		return risenlcUuid;
	}
	public void setRisenlcUuid(Integer risenlcUuid) {
		this.risenlcUuid = risenlcUuid;
	}
	public Integer getRisenlcOrgid() {
		return risenlcOrgid;
	}
	public void setRisenlcOrgid(Integer risenlcOrgid) {
		this.risenlcOrgid = risenlcOrgid;
	}
	public String getRisenlcOrgname() {
		return risenlcOrgname;
	}
	public void setRisenlcOrgname(String risenlcOrgname) {
		this.risenlcOrgname = risenlcOrgname;
	}
	public Integer getRisenlcHolderid() {
		return risenlcHolderid;
	}
	public void setRisenlcHolderid(Integer risenlcHolderid) {
		this.risenlcHolderid = risenlcHolderid;
	}
	public String getRisenlcHoldername() {
		return risenlcHoldername;
	}
	public void setRisenlcHoldername(String risenlcHoldername) {
		this.risenlcHoldername = risenlcHoldername;
	}
	public String getRisenlcMeetingtype() {
		return risenlcMeetingtype;
	}
	public void setRisenlcMeetingtype(String risenlcMeetingtype) {
		this.risenlcMeetingtype = risenlcMeetingtype;
	}
	public String getRisenlcAddress() {
		return risenlcAddress;
	}
	public void setRisenlcAddress(String risenlcAddress) {
		this.risenlcAddress = risenlcAddress;
	}
	public String getRisenlcContactor() {
		return risenlcContactor;
	}
	public void setRisenlcContactor(String risenlcContactor) {
		this.risenlcContactor = risenlcContactor;
	}
	public String getRisenlcParticipant() {
		return risenlcParticipant;
	}
	public void setRisenlcParticipant(String risenlcParticipant) {
		this.risenlcParticipant = risenlcParticipant;
	}
	public Integer getRisenlcExpectdate() {
		return risenlcExpectdate;
	}
	public void setRisenlcExpectdate(Integer risenlcExpectdate) {
		this.risenlcExpectdate = risenlcExpectdate;
	}
	public Date getRisenlcReminddate() {
		return risenlcReminddate;
	}
	public void setRisenlcReminddate(Date risenlcReminddate) {
		this.risenlcReminddate = risenlcReminddate;
	}
	public String getRisenlcComment() {
		return risenlcComment;
	}
	public void setRisenlcComment(String risenlcComment) {
		this.risenlcComment = risenlcComment;
	}
	public Date getRisenlcOdate() {
		return risenlcOdate;
	}
	public void setRisenlcOdate(Date risenlcOdate) {
		this.risenlcOdate = risenlcOdate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStartDate1() {
		return startDate1;
	}
	public void setStartDate1(Date startDate1) {
		this.startDate1 = startDate1;
	}
	public Date getEndDate1() {
		return endDate1;
	}
	public void setEndDate1(Date endDate1) {
		this.endDate1 = endDate1;
	}
	public String getRisenlcContent() {
		return risenlcContent;
	}
	public void setRisenlcContent(String risenlcContent) {
		this.risenlcContent = risenlcContent;
	}
	public String getRisenlcRemark() {
		return risenlcRemark;
	}
	public void setRisenlcRemark(String risenlcRemark) {
		this.risenlcRemark = risenlcRemark;
	}
	
	
}
