package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenFostereducationDao;
import com.risen.entity.RisenFostereducation;

@Repository
public class RisenFostereducationDaoImpl extends HibernateBaseDao<RisenFostereducation, Integer> implements RisenFostereducationDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenFostereducation findById(Integer id) {
		RisenFostereducation entity = get(id);
		return entity;
	}

	public RisenFostereducation save(RisenFostereducation bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenFostereducation deleteById(Integer id) {
		RisenFostereducation entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenFostereducation> getEntityClass() {
		return RisenFostereducation.class;
	}
}