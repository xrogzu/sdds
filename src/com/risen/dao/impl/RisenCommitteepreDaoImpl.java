package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenCommitteepreDao;
import com.risen.entity.RisenCommitteepre;

@Repository
public class RisenCommitteepreDaoImpl extends HibernateBaseDao<RisenCommitteepre, Integer> implements RisenCommitteepreDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenCommitteepre findById(Integer id) {
		RisenCommitteepre entity = get(id);
		return entity;
	}

	public RisenCommitteepre save(RisenCommitteepre bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenCommitteepre deleteById(Integer id) {
		RisenCommitteepre entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenCommitteepre> getEntityClass() {
		return RisenCommitteepre.class;
	}
}