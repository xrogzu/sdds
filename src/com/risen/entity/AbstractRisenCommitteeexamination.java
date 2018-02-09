package com.risen.entity;

import java.util.Date;

/**
 * AbstractRisenCommitteeexamination entity provides the base persistence
 * definition of the RisenCommitteeexamination entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenCommitteeexamination implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer risenceOrgid;
	private String risenceOrgname;
	private Date risenceExaminationdate;
	private String risenceExaminationresult;

	// Constructors

	/** default constructor */
	public AbstractRisenCommitteeexamination() {
	}

	/** full constructor */
	public AbstractRisenCommitteeexamination(Integer risenceOrgid,
			String risenceOrgname, Date risenceExaminationdate,
			String risenceExaminationresult) {
		this.risenceOrgid = risenceOrgid;
		this.risenceOrgname = risenceOrgname;
		this.risenceExaminationdate = risenceExaminationdate;
		this.risenceExaminationresult = risenceExaminationresult;
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

}