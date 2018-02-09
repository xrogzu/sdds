package com.risen.entity;

/**
 * AbstractRisenPoliticalcheck entity provides the base persistence definition
 * of the RisenPoliticalcheck entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenPoliticalcheck implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String risenpcName;
	private String risenpcIdcard;
	private Integer risenpcCheckperid;
	private String risenpcCheckpername;
	private Integer risenpcOrgid;
	private String risenpcOrgname;
	private String risenpcResult;
	private String risenpcComment;

	// Constructors

	/** default constructor */
	public AbstractRisenPoliticalcheck() {
	}

	/** full constructor */
	public AbstractRisenPoliticalcheck(String risenpcName,
			String risenpcIdcard, Integer risenpcCheckperid,
			String risenpcCheckpername, Integer risenpcOrgid,
			String risenpcOrgname, String risenpcResult, String risenpcComment) {
		this.risenpcName = risenpcName;
		this.risenpcIdcard = risenpcIdcard;
		this.risenpcCheckperid = risenpcCheckperid;
		this.risenpcCheckpername = risenpcCheckpername;
		this.risenpcOrgid = risenpcOrgid;
		this.risenpcOrgname = risenpcOrgname;
		this.risenpcResult = risenpcResult;
		this.risenpcComment = risenpcComment;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRisenpcName() {
		return this.risenpcName;
	}

	public void setRisenpcName(String risenpcName) {
		this.risenpcName = risenpcName;
	}

	public String getRisenpcIdcard() {
		return this.risenpcIdcard;
	}

	public void setRisenpcIdcard(String risenpcIdcard) {
		this.risenpcIdcard = risenpcIdcard;
	}

	public Integer getRisenpcCheckperid() {
		return this.risenpcCheckperid;
	}

	public void setRisenpcCheckperid(Integer risenpcCheckperid) {
		this.risenpcCheckperid = risenpcCheckperid;
	}

	public String getRisenpcCheckpername() {
		return this.risenpcCheckpername;
	}

	public void setRisenpcCheckpername(String risenpcCheckpername) {
		this.risenpcCheckpername = risenpcCheckpername;
	}

	public Integer getRisenpcOrgid() {
		return this.risenpcOrgid;
	}

	public void setRisenpcOrgid(Integer risenpcOrgid) {
		this.risenpcOrgid = risenpcOrgid;
	}

	public String getRisenpcOrgname() {
		return this.risenpcOrgname;
	}

	public void setRisenpcOrgname(String risenpcOrgname) {
		this.risenpcOrgname = risenpcOrgname;
	}

	public String getRisenpcResult() {
		return this.risenpcResult;
	}

	public void setRisenpcResult(String risenpcResult) {
		this.risenpcResult = risenpcResult;
	}

	public String getRisenpcComment() {
		return this.risenpcComment;
	}

	public void setRisenpcComment(String risenpcComment) {
		this.risenpcComment = risenpcComment;
	}

}