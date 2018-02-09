package com.risen.entity;

import java.util.Date;

/**
 * AbstractRisenOath entity provides the base persistence definition of the
 * RisenOath entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenOath implements java.io.Serializable {

	// Fields

	private Integer id;
	private String risenohIdcard;
	private String risenohName;
	private String risenohTitle;
	private String risenohAddress;
	private String risenohContent;
	private Date risenohOathdate;

	// Constructors

	/** default constructor */
	public AbstractRisenOath() {
	}

	/** full constructor */
	public AbstractRisenOath(String risenohIdcard, String risenohName,
			String risenohTitle, String risenohAddress, String risenohContent,
			Date risenohOathdate) {
		this.risenohIdcard = risenohIdcard;
		this.risenohName = risenohName;
		this.risenohTitle = risenohTitle;
		this.risenohAddress = risenohAddress;
		this.risenohContent = risenohContent;
		this.risenohOathdate = risenohOathdate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRisenohIdcard() {
		return this.risenohIdcard;
	}

	public void setRisenohIdcard(String risenohIdcard) {
		this.risenohIdcard = risenohIdcard;
	}

	public String getRisenohName() {
		return this.risenohName;
	}

	public void setRisenohName(String risenohName) {
		this.risenohName = risenohName;
	}

	public String getRisenohTitle() {
		return this.risenohTitle;
	}

	public void setRisenohTitle(String risenohTitle) {
		this.risenohTitle = risenohTitle;
	}

	public String getRisenohAddress() {
		return this.risenohAddress;
	}

	public void setRisenohAddress(String risenohAddress) {
		this.risenohAddress = risenohAddress;
	}

	public String getRisenohContent() {
		return this.risenohContent;
	}

	public void setRisenohContent(String risenohContent) {
		this.risenohContent = risenohContent;
	}

	public Date getRisenohOathdate() {
		return this.risenohOathdate;
	}

	public void setRisenohOathdate(Date risenohOathdate) {
		this.risenohOathdate = risenohOathdate;
	}

}