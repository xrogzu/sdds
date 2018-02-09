package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenPartyintroducerDao;
import com.risen.entity.RisenPartyintroducer;

@Repository
public class RisenPartyintroducerDaoImpl extends HibernateBaseDao<RisenPartyintroducer, Integer> implements RisenPartyintroducerDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenPartyintroducer findById(Integer id) {
		RisenPartyintroducer entity = get(id);
		return entity;
	}

	public RisenPartyintroducer save(RisenPartyintroducer bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenPartyintroducer deleteById(Integer id) {
		RisenPartyintroducer entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenPartyintroducer> getEntityClass() {
		return RisenPartyintroducer.class;
	}
}