package com.risen.entity;

import java.util.Date;

/**
 * RisenEducationcheck entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class RisenEducationcheck extends AbstractRisenEducationcheck implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RisenEducationcheck() {
	}

	/** full constructor */
	public RisenEducationcheck(String risenecIdcard, String risenecName,
			Integer risenecCheckperid, String risenecCheckpername,
			Integer risenecOrgid, String risenecOrgname, Date risenecCdate,
			String risenecStatus, String risenecComment) {
		super(risenecIdcard, risenecName, risenecCheckperid,
				risenecCheckpername, risenecOrgid, risenecOrgname,
				risenecCdate, risenecStatus, risenecComment);
	}

}
