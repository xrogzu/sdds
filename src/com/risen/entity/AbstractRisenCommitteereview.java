package com.risen.entity;

import java.util.Date;

/**
 * AbstractRisenCommitteereview entity provides the base persistence definition
 * of the RisenCommitteereview entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenCommitteereview implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer risenceOrgid;
	private String risenceOrgname;
	private Date risenceExaminationdate;
	private String risenceExaminationresult;
	private String risenceIdcard;
	private String risenceName;
	private String risenceComment;

	// Constructors

	/** default constructor */
	public AbstractRisenCommitteereview() {
	}

	/** full constructor */
	public AbstractRisenCommitteereview(Integer risenceOrgid,
			String risenceOrgname, Date risenceExaminationdate,
			String risenceExaminationresult, String risenceIdcard,
			String risenceName, String risenceComment) {
		this.risenceOrgid = risenceOrgid;
		this.risenceOrgname = risenceOrgname;
		this.risenceExaminationdate = risenceExaminationdate;
		this.risenceExaminationresult = risenceExaminationresult;
		this.risenceIdcard = risenceIdcard;
		this.risenceName = risenceName;
		this.risenceComment = risenceComment;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRisenceOrgid() {
		return this.risenceOrgid;
	}

	public void setRisenceOrgid(Integer risenceOrgid) {
		this.risenceOrgid = risenceOrgid;
	}

	public String getRisenceOrgname() {
		return this.risenceOrgname;
	}

	public void setRisenceOrgname(String risenceOrgname) {
		this.risenceOrgname = risenceOrgname;
	}

	public Date getRisenceExaminationdate() {
		return this.risenceExaminationdate;
	}

	public void setRisenceExaminationdate(Date risenceExaminationdate) {
		this.risenceExaminationdate = risenceExaminationdate;
	}

	public String getRisenceExaminationresult() {
		return this.risenceExaminationresult;
	}

	public void setRisenceExaminationresult(String risenceExaminationresult) {
		this.risenceExaminationresult = risenceExaminationresult;
	}

	public String getRisenceIdcard() {
		return this.risenceIdcard;
	}

	public void setRisenceIdcard(String risenceIdcard) {
		this.risenceIdcard = risenceIdcard;
	}

	public String getRisenceName() {
		return this.risenceName;
	}

	public void setRisenceName(String risenceName) {
		this.risenceName = risenceName;
	}

	public String getRisenceComment() {
		return this.risenceComment;
	}

	public void setRisenceComment(String risenceComment) {
		this.risenceComment = risenceComment;
	}

}