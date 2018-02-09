package com.risen.entity;

import java.util.Date;

/**
 * RisenConfirmobject entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class RisenConfirmobject extends AbstractRisenConfirmobject implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RisenConfirmobject() {
	}

	/** full constructor */
	public RisenConfirmobject(String risencoName, String risencoIdcard,
			Integer risencoOrgid, String risencoOrgname, Date risencoConfirmdate,
			String risenComment) {
		super(risencoName, risencoIdcard, risencoOrgid, risencoOrgname,
				risencoConfirmdate, risenComment);
	}

}
