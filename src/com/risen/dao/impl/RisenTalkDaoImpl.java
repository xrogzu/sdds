package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenTalkDao;
import com.risen.entity.RisenTalk;

@Repository
public class RisenTalkDaoImpl extends HibernateBaseDao<RisenTalk, Integer> implements RisenTalkDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenTalk findById(Integer id) {
		RisenTalk entity = get(id);
		return entity;
	}

	public RisenTalk save(RisenTalk bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenTalk deleteById(Integer id) {
		RisenTalk entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenTalk> getEntityClass() {
		return RisenTalk.class;
	}
}