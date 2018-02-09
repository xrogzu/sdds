package com.risen.entity;

import java.util.Date;

/**
 * RisenTalkId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class RisenTalkId extends AbstractRisenTalkId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RisenTalkId() {
	}

	/** minimal constructor */
	public RisenTalkId(Integer id) {
		super(id);
	}

	/** full constructor */
	public RisenTalkId(Integer id, String risentkTorgper, String risentkProposer,
			Date risentkBegindate, String risentkContent, String risentkAddr,
			String risentkCardid, String risentkTalkper, Integer risentkTalkperid,
			Date risentkEnddate, String risentkFlag) {
		super(id, risentkTorgper, risentkProposer, risentkBegindate,
				risentkContent, risentkAddr, risentkCardid, risentkTalkper,
				risentkTalkperid, risentkEnddate, risentkFlag);
	}

}
