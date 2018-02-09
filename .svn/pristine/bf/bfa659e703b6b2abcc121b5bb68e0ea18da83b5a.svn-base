package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenOathDao;
import com.risen.entity.RisenOath;

@Repository
public class RisenOathDaoImpl extends HibernateBaseDao<RisenOath, Integer> implements RisenOathDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenOath findById(Integer id) {
		RisenOath entity = get(id);
		return entity;
	}

	public RisenOath save(RisenOath bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenOath deleteById(Integer id) {
		RisenOath entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenOath> getEntityClass() {
		return RisenOath.class;
	}
}