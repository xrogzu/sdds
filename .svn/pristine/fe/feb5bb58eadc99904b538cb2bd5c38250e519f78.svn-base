package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenConfirmobjectDao;
import com.risen.entity.RisenConfirmobject;

@Repository
public class RisenConfirmobjectDaoImpl extends HibernateBaseDao<RisenConfirmobject, Integer> implements RisenConfirmobjectDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenConfirmobject findById(Integer id) {
		RisenConfirmobject entity = get(id);
		return entity;
	}

	public RisenConfirmobject save(RisenConfirmobject bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenConfirmobject deleteById(Integer id) {
		RisenConfirmobject entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenConfirmobject> getEntityClass() {
		return RisenConfirmobject.class;
	}
}