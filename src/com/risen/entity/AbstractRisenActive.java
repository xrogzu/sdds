package com.risen.entity;

/**
 * AbstractRisenActive entity provides the base persistence definition of the
 * RisenActive entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRisenActive implements java.io.Serializable {

	// Fields

	private RisenActiveId id;

	// Constructors

	/** default constructor */
	public AbstractRisenActive() {
	}

	/** full constructor */
	public AbstractRisenActive(RisenActiveId id) {
		this.id = id;
	}

	// Property accessors

	public RisenActiveId getId() {
		return this.id;
	}

	public void setId(RisenActiveId id) {
		this.id = id;
	}

}