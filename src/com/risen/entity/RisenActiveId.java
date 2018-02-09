package com.risen.entity;

import java.util.Date;

/**
 * RisenActiveId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class RisenActiveId extends AbstractRisenActiveId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RisenActiveId() {
	}

	/** full constructor */
	public RisenActiveId(String risenatPername, Integer id, String risenatCardid,
			Date risenatTime, String risenatInfo, Integer risenatConfirmerid,
			String risenatConfirmer, Integer risenatConfirmerorgid,
			String risenatConfirmerorg) {
		super(risenatPername, id, risenatCardid, risenatTime, risenatInfo,
				risenatConfirmerid, risenatConfirmer, risenatConfirmerorgid,
				risenatConfirmerorg);
	}

}
