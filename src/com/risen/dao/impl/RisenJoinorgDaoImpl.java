package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenJoinorgDao;
import com.risen.entity.RisenJoinorg;

@Repository
public class RisenJoinorgDaoImpl extends HibernateBaseDao<RisenJoinorg, Integer> implements RisenJoinorgDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenJoinorg findById(Integer id) {
		RisenJoinorg entity = get(id);
		return entity;
	}

	public RisenJoinorg save(RisenJoinorg bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenJoinorg deleteById(Integer id) {
		RisenJoinorg entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenJoinorg> getEntityClass() {
		return RisenJoinorg.class;
	}
}