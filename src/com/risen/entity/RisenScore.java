package com.risen.entity;

import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsUser;

public class RisenScore {
	private Integer id;
	private String risenScDescribtion;
	private String risenScScore;
	private CmsUser user;
	private RisenQuota risenScQuota;
	private Integer departId;
	private Integer totalScore;
	private String risenscYear;//年份
	
	public String getRisenscYear() {
		return risenscYear;
	}
	public void setRisenscYear(String risenscYear) {
		this.risenscYear = risenscYear;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRisenScDescribtion() {
		return risenScDescribtion;
	}
	public void setRisenScDescribtion(String risenScDescribtion) {
		this.risenScDescribtion = risenScDescribtion;
	}
	public String getRisenScScore() {
		return risenScScore;
	}
	public void setRisenScScore(String risenScScore) {
		this.risenScScore = risenScScore;
	}
	public CmsUser getUser() {
		return user;
	}
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	public void setUser(CmsUser user) {
		this.user = user;
	}
	public RisenQuota getRisenScQuota() {
		return risenScQuota;
	}
	public void setRisenScQuota(RisenQuota risenScQuota) {
		this.risenScQuota = risenScQuota;
	}
}
