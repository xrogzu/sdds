package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenContactDao;
import com.risen.entity.RisenContact;

@Repository
public class RisenContactDaoImpl extends HibernateBaseDao<RisenContact, Integer> implements RisenContactDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenContact findById(Integer id) {
		RisenContact entity = get(id);
		return entity;
	}

	public RisenContact save(RisenContact bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenContact deleteById(Integer id) {
		RisenContact entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenContact> getEntityClass() {
		return RisenContact.class;
	}
}