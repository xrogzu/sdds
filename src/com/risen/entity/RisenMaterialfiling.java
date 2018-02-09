package com.risen.entity;

import java.util.Date;

/**
 * RisenMaterialfiling entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class RisenMaterialfiling extends AbstractRisenMaterialfiling implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RisenMaterialfiling() {
	}

	/** full constructor */
	public RisenMaterialfiling(String risenmfIdcard, String risenmfName,
			Date risenmfMfdate, Integer risenmfOrgid, String risenmfOrgname,
			String risenmfAddress, String risenmfProcess, String risenmfComment) {
		super(risenmfIdcard, risenmfName, risenmfMfdate, risenmfOrgid,
				risenmfOrgname, risenmfAddress, risenmfProcess, risenmfComment);
	}

}
