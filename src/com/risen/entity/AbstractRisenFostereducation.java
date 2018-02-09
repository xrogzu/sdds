package com.risen.entity;

import java.util.Date;

/**
 * AbstractRisenFostereducation entity provides the base persistence definition
 * of the RisenFostereducation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenFostereducation implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String risenfeName;
	private String risenfeIdcard;
	private Integer risenfeOrgid;
	private String risenfeOrgname;
	private Date risenfeCdate;
	private String risenfeFostered;

	// Constructors

	/** default constructor */
	public AbstractRisenFostereducation() {
	}

	/** full constructor */
	public AbstractRisenFostereducation(String risenfeName,
			String risenfeIdcard, Integer risenfeOrgid, String risenfeOrgname,
			Date risenfeCdate, String risenfeFostered) {
		this.risenfeName = risenfeName;
		this.risenfeIdcard = risenfeIdcard;
		this.risenfeOrgid = risenfeOrgid;
		this.risenfeOrgname = risenfeOrgname;
		this.risenfeCdate = risenfeCdate;
		this.risenfeFostered = risenfeFostered;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRisenfeName() {
		return this.risenfeName;
	}

	public void setRisenfeName(String risenfeName) {
		this.risenfeName = risenfeName;
	}

	public String getRisenfeIdcard() {
		return this.risenfeIdcard;
	}

	public void setRisenfeIdcard(String risenfeIdcard) {
		this.risenfeIdcard = risenfeIdcard;
	}

	public Integer getRisenfeOrgid() {
		return this.risenfeOrgid;
	}

	public void setRisenfeOrgid(Integer risenfeOrgid) {
		this.risenfeOrgid = risenfeOrgid;
	}

	public String getRisenfeOrgname() {
		return this.risenfeOrgname;
	}

	public void setRisenfeOrgname(String risenfeOrgname) {
		this.risenfeOrgname = risenfeOrgname;
	}

	public Date getRisenfeCdate() {
		return this.risenfeCdate;
	}

	public void setRisenfeCdate(Date risenfeCdate) {
		this.risenfeCdate = risenfeCdate;
	}

	public String getRisenfeFostered() {
		return this.risenfeFostered;
	}

	public void setRisenfeFostered(String risenfeFostered) {
		this.risenfeFostered = risenfeFostered;
	}

}