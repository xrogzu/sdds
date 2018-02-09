package com.risen.entity;

import java.util.Date;

/**
 * AbstractRisenPartyapplication entity provides the base persistence definition
 * of the RisenPartyapplication entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenPartyapplication implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String risenpaName;
	private Integer risenpaSubmitperid;
	private String risenpaSubmitpername;
	private Integer risenpaExamineperid;
	private String risenpaExaminepername;
	private Integer risenpaExamineorgid;
	private String risenpaExamineorgname;
	private Date risenpaSubmitdate;
	private Date risenpaExaminedate;
	private String risenpaResult;
	private String risenpaApplicationcontent;
	private String risenpaExaminecontent;
	private String risenpaSubmitcardid;
	private String risenpaSubmitsex;
	private Date risenpaSubmitbirthday;

	// Constructors

	/** default constructor */
	public AbstractRisenPartyapplication() {
	}

	/** full constructor */
	public AbstractRisenPartyapplication(String risenpaName,
			Integer risenpaSubmitperid, String risenpaSubmitpername,
			Integer risenpaExamineperid, String risenpaExaminepername,
			Integer risenpaExamineorgid, String risenpaExamineorgname,
			Date risenpaSubmitdate, Date risenpaExaminedate,
			String risenpaResult, String risenpaApplicationcontent,
			String risenpaExaminecontent, String risenpaSubmitcardid,
			String risenpaSubmitsex, Date risenpaSubmitbirthday) {
		this.risenpaName = risenpaName;
		this.risenpaSubmitperid = risenpaSubmitperid;
		this.risenpaSubmitpername = risenpaSubmitpername;
		this.risenpaExamineperid = risenpaExamineperid;
		this.risenpaExaminepername = risenpaExaminepername;
		this.risenpaExamineorgid = risenpaExamineorgid;
		this.risenpaExamineorgname = risenpaExamineorgname;
		this.risenpaSubmitdate = risenpaSubmitdate;
		this.risenpaExaminedate = risenpaExaminedate;
		this.risenpaResult = risenpaResult;
		this.risenpaApplicationcontent = risenpaApplicationcontent;
		this.risenpaExaminecontent = risenpaExaminecontent;
		this.risenpaSubmitcardid = risenpaSubmitcardid;
		this.risenpaSubmitsex = risenpaSubmitsex;
		this.risenpaSubmitbirthday = risenpaSubmitbirthday;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRisenpaName() {
		return this.risenpaName;
	}

	public void setRisenpaName(String risenpaName) {
		this.risenpaName = risenpaName;
	}

	public Integer getRisenpaSubmitperid() {
		return this.risenpaSubmitperid;
	}

	public void setRisenpaSubmitperid(Integer risenpaSubmitperid) {
		this.risenpaSubmitperid = risenpaSubmitperid;
	}

	public String getRisenpaSubmitpername() {
		return this.risenpaSubmitpername;
	}

	public void setRisenpaSubmitpername(String risenpaSubmitpername) {
		this.risenpaSubmitpername = risenpaSubmitpername;
	}

	public Integer getRisenpaExamineperid() {
		return this.risenpaExamineperid;
	}

	public void setRisenpaExamineperid(Integer risenpaExamineperid) {
		this.risenpaExamineperid = risenpaExamineperid;
	}

	public String getRisenpaExaminepername() {
		return this.risenpaExaminepername;
	}

	public void setRisenpaExaminepername(String risenpaExaminepername) {
		this.risenpaExaminepername = risenpaExaminepername;
	}

	public Integer getRisenpaExamineorgid() {
		return this.risenpaExamineorgid;
	}

	public void setRisenpaExamineorgid(Integer risenpaExamineorgid) {
		this.risenpaExamineorgid = risenpaExamineorgid;
	}

	public String getRisenpaExamineorgname() {
		return this.risenpaExamineorgname;
	}

	public void setRisenpaExamineorgname(String risenpaExamineorgname) {
		this.risenpaExamineorgname = risenpaExamineorgname;
	}

	public Date getRisenpaSubmitdate() {
		return this.risenpaSubmitdate;
	}

	public void setRisenpaSubmitdate(Date risenpaSubmitdate) {
		this.risenpaSubmitdate = risenpaSubmitdate;
	}

	public Date getRisenpaExaminedate() {
		return this.risenpaExaminedate;
	}

	public void setRisenpaExaminedate(Date risenpaExaminedate) {
		this.risenpaExaminedate = risenpaExaminedate;
	}

	public String getRisenpaResult() {
		return this.risenpaResult;
	}

	public void setRisenpaResult(String risenpaResult) {
		this.risenpaResult = risenpaResult;
	}

	public String getRisenpaApplicationcontent() {
		return this.risenpaApplicationcontent;
	}

	public void setRisenpaApplicationcontent(String risenpaApplicationcontent) {
		this.risenpaApplicationcontent = risenpaApplicationcontent;
	}

	public String getRisenpaExaminecontent() {
		return this.risenpaExaminecontent;
	}

	public void setRisenpaExaminecontent(String risenpaExaminecontent) {
		this.risenpaExaminecontent = risenpaExaminecontent;
	}

	public String getRisenpaSubmitcardid() {
		return this.risenpaSubmitcardid;
	}

	public void setRisenpaSubmitcardid(String risenpaSubmitcardid) {
		this.risenpaSubmitcardid = risenpaSubmitcardid;
	}

	public String getRisenpaSubmitsex() {
		return this.risenpaSubmitsex;
	}

	public void setRisenpaSubmitsex(String risenpaSubmitsex) {
		this.risenpaSubmitsex = risenpaSubmitsex;
	}

	public Date getRisenpaSubmitbirthday() {
		return this.risenpaSubmitbirthday;
	}

	public void setRisenpaSubmitbirthday(Date risenpaSubmitbirthday) {
		this.risenpaSubmitbirthday = risenpaSubmitbirthday;
	}

}