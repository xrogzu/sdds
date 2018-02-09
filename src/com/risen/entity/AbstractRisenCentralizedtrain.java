package com.risen.entity;

import java.util.Date;

/**
 * AbstractRisenCentralizedtrain entity provides the base persistence definition
 * of the RisenCentralizedtrain entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenCentralizedtrain implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String risenctIdcard;
	private String risenctName;
	private String risenctTitle;
	private Date risenctBegindate;
	private Date risenctEnddate;
	private String risenctAddress;
	private String risenctContent;

	// Constructors

	/** default constructor */
	public AbstractRisenCentralizedtrain() {
	}

	/** full constructor */
	public AbstractRisenCentralizedtrain(String risenctIdcard,
			String risenctName, String risenctTitle, Date risenctBegindate,
			Date risenctEnddate, String risenctAddress, String risenctContent) {
		this.risenctIdcard = risenctIdcard;
		this.risenctName = risenctName;
		this.risenctTitle = risenctTitle;
		this.risenctBegindate = risenctBegindate;
		this.risenctEnddate = risenctEnddate;
		this.risenctAddress = risenctAddress;
		this.risenctContent = risenctContent;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRisenctIdcard() {
		return this.risenctIdcard;
	}

	public void setRisenctIdcard(String risenctIdcard) {
		this.risenctIdcard = risenctIdcard;
	}

	public String getRisenctName() {
		return this.risenctName;
	}

	public void setRisenctName(String risenctName) {
		this.risenctName = risenctName;
	}

	public String getRisenctTitle() {
		return this.risenctTitle;
	}

	public void setRisenctTitle(String risenctTitle) {
		this.risenctTitle = risenctTitle;
	}

	public Date getRisenctBegindate() {
		return this.risenctBegindate;
	}

	public void setRisenctBegindate(Date risenctBegindate) {
		this.risenctBegindate = risenctBegindate;
	}

	public Date getRisenctEnddate() {
		return this.risenctEnddate;
	}

	public void setRisenctEnddate(Date risenctEnddate) {
		this.risenctEnddate = risenctEnddate;
	}

	public String getRisenctAddress() {
		return this.risenctAddress;
	}

	public void setRisenctAddress(String risenctAddress) {
		this.risenctAddress = risenctAddress;
	}

	public String getRisenctContent() {
		return this.risenctContent;
	}

	public void setRisenctContent(String risenctContent) {
		this.risenctContent = risenctContent;
	}

}