package com.risen.entity;

/**
 * RisenPoliticalcheck entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class RisenPoliticalcheck extends AbstractRisenPoliticalcheck implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RisenPoliticalcheck() {
	}

	/** full constructor */
	public RisenPoliticalcheck(String risenpcName, String risenpcIdcard,
			Integer risenpcCheckperid, String risenpcCheckpername,
			Integer risenpcOrgid, String risenpcOrgname, String risenpcResult,
			String risenpcComment) {
		super(risenpcName, risenpcIdcard, risenpcCheckperid,
				risenpcCheckpername, risenpcOrgid, risenpcOrgname,
				risenpcResult, risenpcComment);
	}

}
