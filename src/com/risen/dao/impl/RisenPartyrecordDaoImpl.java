package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenPartyrecordDao;
import com.risen.entity.RisenPartyrecord;

@Repository
public class RisenPartyrecordDaoImpl extends HibernateBaseDao<RisenPartyrecord, Integer> implements RisenPartyrecordDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenPartyrecord findById(Integer id) {
		RisenPartyrecord entity = get(id);
		return entity;
	}

	public RisenPartyrecord save(RisenPartyrecord bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenPartyrecord deleteById(Integer id) {
		RisenPartyrecord entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenPartyrecord> getEntityClass() {
		return RisenPartyrecord.class;
	}
}