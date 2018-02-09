package com.risen.entity;

/**
 * AbstractRisenTalk entity provides the base persistence definition of the
 * RisenTalk entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenTalk implements java.io.Serializable {

	// Fields

	private RisenTalkId id;

	// Constructors

	/** default constructor */
	public AbstractRisenTalk() {
	}

	/** full constructor */
	public AbstractRisenTalk(RisenTalkId id) {
		this.id = id;
	}

	// Property accessors

	public RisenTalkId getId() {
		return this.id;
	}

	public void setId(RisenTalkId id) {
		this.id = id;
	}

}