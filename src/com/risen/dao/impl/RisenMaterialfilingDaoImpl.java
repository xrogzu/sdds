package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenMaterialfilingDao;
import com.risen.entity.RisenMaterialfiling;

@Repository
public class RisenMaterialfilingDaoImpl extends HibernateBaseDao<RisenMaterialfiling, Integer> implements RisenMaterialfilingDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenMaterialfiling findById(Integer id) {
		RisenMaterialfiling entity = get(id);
		return entity;
	}

	public RisenMaterialfiling save(RisenMaterialfiling bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenMaterialfiling deleteById(Integer id) {
		RisenMaterialfiling entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenMaterialfiling> getEntityClass() {
		return RisenMaterialfiling.class;
	}
}