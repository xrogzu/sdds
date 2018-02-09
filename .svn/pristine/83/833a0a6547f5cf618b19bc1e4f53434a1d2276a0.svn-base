package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenMeetingdiscussDao;
import com.risen.entity.RisenMeetingdiscuss;

@Repository
public class RisenMeetingdiscussDaoImpl extends HibernateBaseDao<RisenMeetingdiscuss, Integer> implements RisenMeetingdiscussDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenMeetingdiscuss findById(Integer id) {
		RisenMeetingdiscuss entity = get(id);
		return entity;
	}

	public RisenMeetingdiscuss save(RisenMeetingdiscuss bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenMeetingdiscuss deleteById(Integer id) {
		RisenMeetingdiscuss entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenMeetingdiscuss> getEntityClass() {
		return RisenMeetingdiscuss.class;
	}
}