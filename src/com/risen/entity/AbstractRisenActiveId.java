package com.risen.entity;

import java.util.Date;

/**
 * AbstractRisenActiveId entity provides the base persistence definition of the
 * RisenActiveId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenActiveId implements java.io.Serializable {

	// Fields

	private String risenatPername;
	private Integer id;
	private String risenatCardid;
	private Date risenatTime;
	private String risenatInfo;
	private Integer risenatConfirmerid;
	private String risenatConfirmer;
	private Integer risenatConfirmerorgid;
	private String risenatConfirmerorg;

	// Constructors

	/** default constructor */
	public AbstractRisenActiveId() {
	}

	/** full constructor */
	public AbstractRisenActiveId(String risenatPername, Integer id,
			String risenatCardid, Date risenatTime, String risenatInfo,
			Integer risenatConfirmerid, String risenatConfirmer,
			Integer risenatConfirmerorgid, String risenatConfirmerorg) {
		this.risenatPername = risenatPername;
		this.id = id;
		this.risenatCardid = risenatCardid;
		this.risenatTime = risenatTime;
		this.risenatInfo = risenatInfo;
		this.risenatConfirmerid = risenatConfirmerid;
		this.risenatConfirmer = risenatConfirmer;
		this.risenatConfirmerorgid = risenatConfirmerorgid;
		this.risenatConfirmerorg = risenatConfirmerorg;
	}

	// Property accessors

	public String getRisenatPername() {
		return this.risenatPername;
	}

	public void setRisenatPername(String risenatPername) {
		this.risenatPername = risenatPername;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRisenatCardid() {
		return this.risenatCardid;
	}

	public void setRisenatCardid(String risenatCardid) {
		this.risenatCardid = risenatCardid;
	}

	public Date getRisenatTime() {
		return this.risenatTime;
	}

	public void setRisenatTime(Date risenatTime) {
		this.risenatTime = risenatTime;
	}

	public String getRisenatInfo() {
		return this.risenatInfo;
	}

	public void setRisenatInfo(String risenatInfo) {
		this.risenatInfo = risenatInfo;
	}

	public Integer getRisenatConfirmerid() {
		return this.risenatConfirmerid;
	}

	public void setRisenatConfirmerid(Integer risenatConfirmerid) {
		this.risenatConfirmerid = risenatConfirmerid;
	}

	public String getRisenatConfirmer() {
		return this.risenatConfirmer;
	}

	public void setRisenatConfirmer(String risenatConfirmer) {
		this.risenatConfirmer = risenatConfirmer;
	}

	public Integer getRisenatConfirmerorgid() {
		return this.risenatConfirmerorgid;
	}

	public void setRisenatConfirmerorgid(Integer risenatConfirmerorgid) {
		this.risenatConfirmerorgid = risenatConfirmerorgid;
	}

	public String getRisenatConfirmerorg() {
		return this.risenatConfirmerorg;
	}

	public void setRisenatConfirmerorg(String risenatConfirmerorg) {
		this.risenatConfirmerorg = risenatConfirmerorg;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractRisenActiveId))
			return false;
		AbstractRisenActiveId castOther = (AbstractRisenActiveId) other;

		return ((this.getRisenatPername() == castOther.getRisenatPername()) || (this
				.getRisenatPername() != null
				&& castOther.getRisenatPername() != null && this
				.getRisenatPername().equals(castOther.getRisenatPername())))
				&& ((this.getId() == castOther.getId()) || (this.getId() != null
						&& castOther.getId() != null && this.getId().equals(
						castOther.getId())))
				&& ((this.getRisenatCardid() == castOther.getRisenatCardid()) || (this
						.getRisenatCardid() != null
						&& castOther.getRisenatCardid() != null && this
						.getRisenatCardid()
						.equals(castOther.getRisenatCardid())))
				&& ((this.getRisenatTime() == castOther.getRisenatTime()) || (this
						.getRisenatTime() != null
						&& castOther.getRisenatTime() != null && this
						.getRisenatTime().equals(castOther.getRisenatTime())))
				&& ((this.getRisenatInfo() == castOther.getRisenatInfo()) || (this
						.getRisenatInfo() != null
						&& castOther.getRisenatInfo() != null && this
						.getRisenatInfo().equals(castOther.getRisenatInfo())))
				&& ((this.getRisenatConfirmerid() == castOther
						.getRisenatConfirmerid()) || (this
						.getRisenatConfirmerid() != null
						&& castOther.getRisenatConfirmerid() != null && this
						.getRisenatConfirmerid().equals(
								castOther.getRisenatConfirmerid())))
				&& ((this.getRisenatConfirmer() == castOther
						.getRisenatConfirmer()) || (this.getRisenatConfirmer() != null
						&& castOther.getRisenatConfirmer() != null && this
						.getRisenatConfirmer().equals(
								castOther.getRisenatConfirmer())))
				&& ((this.getRisenatConfirmerorgid() == castOther
						.getRisenatConfirmerorgid()) || (this
						.getRisenatConfirmerorgid() != null
						&& castOther.getRisenatConfirmerorgid() != null && this
						.getRisenatConfirmerorgid().equals(
								castOther.getRisenatConfirmerorgid())))
				&& ((this.getRisenatConfirmerorg() == castOther
						.getRisenatConfirmerorg()) || (this
						.getRisenatConfirmerorg() != null
						&& castOther.getRisenatConfirmerorg() != null && this
						.getRisenatConfirmerorg().equals(
								castOther.getRisenatConfirmerorg())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getRisenatPername() == null ? 0 : this.getRisenatPername()
						.hashCode());
		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37
				* result
				+ (getRisenatCardid() == null ? 0 : this.getRisenatCardid()
						.hashCode());
		result = 37
				* result
				+ (getRisenatTime() == null ? 0 : this.getRisenatTime()
						.hashCode());
		result = 37
				* result
				+ (getRisenatInfo() == null ? 0 : this.getRisenatInfo()
						.hashCode());
		result = 37
				* result
				+ (getRisenatConfirmerid() == null ? 0 : this
						.getRisenatConfirmerid().hashCode());
		result = 37
				* result
				+ (getRisenatConfirmer() == null ? 0 : this
						.getRisenatConfirmer().hashCode());
		result = 37
				* result
				+ (getRisenatConfirmerorgid() == null ? 0 : this
						.getRisenatConfirmerorgid().hashCode());
		result = 37
				* result
				+ (getRisenatConfirmerorg() == null ? 0 : this
						.getRisenatConfirmerorg().hashCode());
		return result;
	}

}