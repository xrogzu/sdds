package com.risen.entity;

import java.util.Date;

/**
 * AbstractRisenPartyrecord entity provides the base persistence definition of
 * the RisenPartyrecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenPartyrecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Date risenprRecorddate;
	private Integer risenprOrgid;
	private String risenprOrgname;
	private String risenprProcess;
	private String risenprIdcard;
	private String risenprName;
	private String risenprFlag;

	// Constructors

	/** default constructor */
	public AbstractRisenPartyrecord() {
	}

	/** full constructor */
	public AbstractRisenPartyrecord(Date risenprRecorddate, Integer risenprOrgid,
			String risenprOrgname, String risenprProcess, String risenprIdcard,
			String risenprName, String risenprFlag) {
		this.risenprRecorddate = risenprRecorddate;
		this.risenprOrgid = risenprOrgid;
		this.risenprOrgname = risenprOrgname;
		this.risenprProcess = risenprProcess;
		this.risenprIdcard = risenprIdcard;
		this.risenprName = risenprName;
		this.risenprFlag = risenprFlag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRisenprRecorddate() {
		return this.risenprRecorddate;
	}

	public void setRisenprRecorddate(Date risenprRecorddate) {
		this.risenprRecorddate = risenprRecorddate;
	}

	public Integer getRisenprOrgid() {
		return this.risenprOrgid;
	}

	public void setRisenprOrgid(Integer risenprOrgid) {
		this.risenprOrgid = risenprOrgid;
	}

	public String getRisenprOrgname() {
		return this.risenprOrgname;
	}

	public void setRisenprOrgname(String risenprOrgname) {
		this.risenprOrgname = risenprOrgname;
	}

	public String getRisenprProcess() {
		return this.risenprProcess;
	}

	public void setRisenprProcess(String risenprProcess) {
		this.risenprProcess = risenprProcess;
	}

	public String getRisenprIdcard() {
		return this.risenprIdcard;
	}

	public void setRisenprIdcard(String risenprIdcard) {
		this.risenprIdcard = risenprIdcard;
	}

	public String getRisenprName() {
		return this.risenprName;
	}

	public void setRisenprName(String risenprName) {
		this.risenprName = risenprName;
	}

	public String getRisenprFlag() {
		return this.risenprFlag;
	}

	public void setRisenprFlag(String risenprFlag) {
		this.risenprFlag = risenprFlag;
	}

}