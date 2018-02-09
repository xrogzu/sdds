package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenCommitteereviewDao;
import com.risen.entity.RisenCommitteereview;

@Repository
public class RisenCommitteereviewDaoImpl extends HibernateBaseDao<RisenCommitteereview, Integer> implements RisenCommitteereviewDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenCommitteereview findById(Integer id) {
		RisenCommitteereview entity = get(id);
		return entity;
	}

	public RisenCommitteereview save(RisenCommitteereview bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenCommitteereview deleteById(Integer id) {
		RisenCommitteereview entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenCommitteereview> getEntityClass() {
		return RisenCommitteereview.class;
	}
}