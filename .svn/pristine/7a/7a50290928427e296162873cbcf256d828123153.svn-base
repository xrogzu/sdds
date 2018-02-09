package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenCentralizedtrainDao;
import com.risen.entity.RisenCentralizedtrain;

@Repository
public class RisenCentralizedtrainDaoImpl extends HibernateBaseDao<RisenCentralizedtrain, Integer> implements RisenCentralizedtrainDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenCentralizedtrain findById(Integer id) {
		RisenCentralizedtrain entity = get(id);
		return entity;
	}

	public RisenCentralizedtrain save(RisenCentralizedtrain bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenCentralizedtrain deleteById(Integer id) {
		RisenCentralizedtrain entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenCentralizedtrain> getEntityClass() {
		return RisenCentralizedtrain.class;
	}
}