package com.risen.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenCommitteeexaminationDao;
import com.risen.entity.RisenCommitteeexamination;

@Repository
public class RisenCommitteeexaminationDaoImpl extends HibernateBaseDao<RisenCommitteeexamination, Integer> implements RisenCommitteeexaminationDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public RisenCommitteeexamination findById(Integer id) {
		RisenCommitteeexamination entity = get(id);
		return entity;
	}

	public RisenCommitteeexamination save(RisenCommitteeexamination bean) {
		getSession().save(bean);
		return bean;
	}

	public RisenCommitteeexamination deleteById(Integer id) {
		RisenCommitteeexamination entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<RisenCommitteeexamination> getEntityClass() {
		return RisenCommitteeexamination.class;
	}
}