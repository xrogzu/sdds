package com.risen.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.risen.dao.ICmsAppealMailReplayDao;
import com.risen.entity.CmsAppealMailReplay;
import com.risen.entity.RisenTargetDetail;


@Repository
public class CmsAppealMailReplayDaoImpl extends HibernateBaseDao<CmsAppealMailReplay, Integer> implements ICmsAppealMailReplayDao {

	@Override
	public CmsAppealMailReplay save(CmsAppealMailReplay bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<CmsAppealMailReplay> getEntityClass() {
		// TODO Auto-generated method stub
		return CmsAppealMailReplay.class;
	}

	@Override
	public List<CmsAppealMailReplay> getReplay(Integer appealId) {
		// TODO Auto-generated method stub
		String hql = "from CmsAppealMailReplay where appeal_Id="+appealId;
		Query query = getSession().createQuery(hql);
		List<CmsAppealMailReplay> list = (List<CmsAppealMailReplay>)query.list();
		return list;
	}

}
