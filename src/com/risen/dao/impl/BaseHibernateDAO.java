package com.risen.dao.impl;

import org.hibernate.Session;

import com.risen.HibernateSessionFactory;
import com.risen.dao.IBaseHibernateDAO;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
}