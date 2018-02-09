package com.risen.entity;

import java.util.Date;

/**
 * AbstractRisenContact entity provides the base persistence definition of the
 * RisenContact entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenContact implements java.io.Serializable {

	// Fields

	private Integer id;
	private String risenctName;
	private String risenctIdcard;
	private String risenctContactname;
	private Date risenctAppointtime;
	private String risenctComment;
	private Integer risenctContactid;

	// Constructors

	/** default constructor */
	public AbstractRisenContact() {
	}

	/** full constructor */
	public AbstractRisenContact(String risenctName, String risenctIdcard,
			String risenctContactname, Date risenctAppointtime,
			String risenctComment, Integer risenctContactid) {
		this.risenctName = risenctName;
		this.risenctIdcard = risenctIdcard;
		this.risenctContactname = risenctContactname;
		this.risenctAppointtime = risenctAppointtime;
		this.risenctComment = risenctComment;
		this.risenctContactid = risenctContactid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRisenctName() {
		return this.risenctName;
	}

	public void setRisenctName(String risenctName) {
		this.risenctName = risenctName;
	}

	public String getRisenctIdcard() {
		return this.risenctIdcard;
	}

	public void setRisenctIdcard(String risenctIdcard) {
		this.risenctIdcard = risenctIdcard;
	}

	public String getRisenctContactname() {
		return this.risenctContactname;
	}

	public void setRisenctContactname(String risenctContactname) {
		this.risenctContactname = risenctContactname;
	}

	public Date getRisenctAppointtime() {
		return this.risenctAppointtime;
	}

	public void setRisenctAppointtime(Date risenctAppointtime) {
		this.risenctAppointtime = risenctAppointtime;
	}

	public String getRisenctComment() {
		return this.risenctComment;
	}

	public void setRisenctComment(String risenctComment) {
		this.risenctComment = risenctComment;
	}

	public Integer getRisenctContactid() {
		return this.risenctContactid;
	}

	public void setRisenctContactid(Integer risenctContactid) {
		this.risenctContactid = risenctContactid;
	}

}