package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenActiveDao;
import com.risen.entity.RisenActive;

@Repository
public class RisenActiveDaoImpl extends HibernateBaseDao<RisenActive, Integer> implements RisenActiveDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenActive findById(Integer id) {
		RisenActive entity = get(id);
		return entity;
	}

	public RisenActive save(RisenActive bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenActive deleteById(Integer id) {
		RisenActive entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenActive> getEntityClass() {
		return RisenActive.class;
	}
}