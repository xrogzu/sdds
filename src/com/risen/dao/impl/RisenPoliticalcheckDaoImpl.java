package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenPoliticalcheckDao;
import com.risen.entity.RisenPoliticalcheck;

@Repository
public class RisenPoliticalcheckDaoImpl extends HibernateBaseDao<RisenPoliticalcheck, Integer> implements RisenPoliticalcheckDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenPoliticalcheck findById(Integer id) {
		RisenPoliticalcheck entity = get(id);
		return entity;
	}

	public RisenPoliticalcheck save(RisenPoliticalcheck bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenPoliticalcheck deleteById(Integer id) {
		RisenPoliticalcheck entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenPoliticalcheck> getEntityClass() {
		return RisenPoliticalcheck.class;
	}
}