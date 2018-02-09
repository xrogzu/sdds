package com.risen.entity;

import java.util.Set;

public class RisenQuota {
	private Integer id;
	private String risenqtName;
	private Integer risenqtScore;
	private Set<RisenScore> risenScores;
	private Integer quotaDepartId;
	private String quotaDepartName;
	private Integer risenqtType;
	private Integer risenqtAddType;
	private String risenqtYear;//年份
	private String risenqtTotal;//总分
	
	
	
	public String getRisenqtYear() {
		return risenqtYear;
	}
	public void setRisenqtYear(String risenqtYear) {
		this.risenqtYear = risenqtYear;
	}
	public String getRisenqtTotal() {
		return risenqtTotal;
	}
	public void setRisenqtTotal(String risenqtTotal) {
		this.risenqtTotal = risenqtTotal;
	}
	public Integer getRisenqtType() {
		return risenqtType;
	}
	public void setRisenqtType(Integer risenqtType) {
		this.risenqtType = risenqtType;
	}
	public Integer getRisenqtAddType() {
		return risenqtAddType;
	}
	public void setRisenqtAddType(Integer risenqtAddType) {
		this.risenqtAddType = risenqtAddType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRisenqtName() {
		return risenqtName;
	}
	public void setRisenqtName(String risenqtName) {
		this.risenqtName = risenqtName;
	}
	public Integer getRisenqtScore() {
		return risenqtScore;
	}
	public void setRisenqtScore(Integer risenqtScore) {
		this.risenqtScore = risenqtScore;
	}
	public Set<RisenScore> getRisenScores() {
		return risenScores;
	}
	public void setRisenScores(Set<RisenScore> risenScores) {
		this.risenScores = risenScores;
	}
	public Integer getQuotaDepartId() {
		return quotaDepartId;
	}
	public void setQuotaDepartId(Integer quotaDepartId) {
		this.quotaDepartId = quotaDepartId;
	}
	public String getQuotaDepartName() {
		return quotaDepartName;
	}
	public void setQuotaDepartName(String quotaDepartName) {
		this.quotaDepartName = quotaDepartName;
	}
	
}
