package com.risen.dao.impl;

import org.hibernate.Criteria;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenPublicnotifyDao;
import com.risen.entity.RisenPublicnotify;

public class RisenPublicnotifyDaoImpl extends HibernateBaseDao<RisenPublicnotify, Integer> implements IRisenPublicnotifyDao {

	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenPublicnotify findById(Integer id) {
		RisenPublicnotify entity = super.get(id);
		return entity;
	}

	public RisenPublicnotify save(RisenPublicnotify bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenPublicnotify update(RisenPublicnotify bean) {
		getSession().update(bean);
		return bean;
	}

	public RisenPublicnotify deleteById(Integer id) {
		RisenPublicnotify entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<RisenPublicnotify> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenPublicnotify.class;
	}

	
}
