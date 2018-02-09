package com.risen.dao.impl;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.risen.dao.IRisenEnsureActiveDao;
import com.risen.entity.RisenActive;

public class RisenEnsureActiveDao extends HibernateBaseDao<RisenActive, Integer> implements IRisenEnsureActiveDao {

	public RisenActive save(RisenActive bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<RisenActive> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
