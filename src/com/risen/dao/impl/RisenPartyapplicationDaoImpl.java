package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenPartyapplicationDao;
import com.risen.entity.RisenPartyapplication;

@Repository
public class RisenPartyapplicationDaoImpl extends HibernateBaseDao<RisenPartyapplication, Integer> implements RisenPartyapplicationDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenPartyapplication findById(Integer id) {
		RisenPartyapplication entity = get(id);
		return entity;
	}

	public RisenPartyapplication save(RisenPartyapplication bean) {
		getSession().save(bean); 
		return bean;
	}

	public RisenPartyapplication deleteById(Integer id) {
		RisenPartyapplication entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenPartyapplication> getEntityClass() {
		return RisenPartyapplication.class;
	}
}