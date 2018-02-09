package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenEducationcheckDao;
import com.risen.entity.RisenEducationcheck;

@Repository
public class RisenEducationcheckDaoImpl extends HibernateBaseDao<RisenEducationcheck, Integer> implements RisenEducationcheckDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenEducationcheck findById(Integer id) {
		RisenEducationcheck entity = get(id);
		return entity;
	}

	public RisenEducationcheck save(RisenEducationcheck bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenEducationcheck deleteById(Integer id) {
		RisenEducationcheck entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenEducationcheck> getEntityClass() {
		return RisenEducationcheck.class;
	}
}