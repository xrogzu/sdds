package com.risen.entity;

import java.util.Date;

/**
 * RisenPartyrecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class RisenPartyrecord extends AbstractRisenPartyrecord implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RisenPartyrecord() {
	}

	/** full constructor */
	public RisenPartyrecord(Date risenprRecorddate, Integer risenprOrgid,
			String risenprOrgname, String risenprProcess, String risenprIdcard,
			String risenprName, String risenprFlag) {
		super(risenprRecorddate, risenprOrgid, risenprOrgname, risenprProcess,
				risenprIdcard, risenprName, risenprFlag);
	}

}
