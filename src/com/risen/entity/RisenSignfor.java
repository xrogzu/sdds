package com.risen.entity;

import java.util.Date;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.core.entity.CmsDepartment;

public class RisenSignfor {
	private Integer risensfUuid;//编号
	private CmsDepartment risensfDept;//组织编号
	private Content risensfContent;//内容编号
	private String risensfIssign;//是否签收
	private String risensfIp;//签收IP
	private String risensfDate;//签收日期
	public Integer getRisensfUuid() {
		return risensfUuid;
	}
	public void setRisensfUuid(Integer risensfUuid) {
		this.risensfUuid = risensfUuid;
	}
	public CmsDepartment getRisensfDept() {
		return risensfDept;
	}
	public void setRisensfDept(CmsDepartment risensfDept) {
		this.risensfDept = risensfDept;
	}
	public Content getRisensfContent() {
		return risensfContent;
	}
	public void setRisensfContent(Content risensfContent) {
		this.risensfContent = risensfContent;
	}
	public String getRisensfIssign() {
		return risensfIssign;
	}
	public void setRisensfIssign(String risensfIssign) {
		this.risensfIssign = risensfIssign;
	}
	public String getRisensfIp() {
		return risensfIp;
	}
	public void setRisensfIp(String risensfIp) {
		this.risensfIp = risensfIp;
	}
	public String getRisensfDate() {
		return risensfDate;
	}
	public void setRisensfDate(String risensfDate) {
		this.risensfDate = risensfDate;
	}
}
