package com.risen.entity;

import java.util.Date;

/**
 * RisenPartyapplication entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class RisenPartyapplication extends AbstractRisenPartyapplication
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public RisenPartyapplication() {
	}

	/** full constructor */
	public RisenPartyapplication(String risenpaName, Integer risenpaSubmitperid,
			String risenpaSubmitpername, Integer risenpaExamineperid,
			String risenpaExaminepername, Integer risenpaExamineorgid,
			String risenpaExamineorgname, Date risenpaSubmitdate,
			Date risenpaExaminedate, String risenpaResult,
			String risenpaApplicationcontent, String risenpaExaminecontent,
			String risenpaSubmitcardid, String risenpaSubmitsex,
			Date risenpaSubmitbirthday) {
		super(risenpaName, risenpaSubmitperid, risenpaSubmitpername,
				risenpaExamineperid, risenpaExaminepername,
				risenpaExamineorgid, risenpaExamineorgname, risenpaSubmitdate,
				risenpaExaminedate, risenpaResult, risenpaApplicationcontent,
				risenpaExaminecontent, risenpaSubmitcardid, risenpaSubmitsex,
				risenpaSubmitbirthday);
	}

}
