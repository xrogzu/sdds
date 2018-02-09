package com.risen.dao.impl;

import org.hibernate.Query;
import org.springframework.util.StringUtils;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenDiscussionDao;
import com.risen.entity.RisenDiscussion;
import com.risen.entity.RisenTarget;

public class RisenDiscussionImpl extends HibernateBaseDao<RisenDiscussion, Integer> implements
		IRisenDiscussionDao {

	@Override
	public Pagination getPage(Integer userId, Integer departId, Integer year) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from RisenDiscussion where 1=1");
		if(StringUtils.hasText(String.valueOf(userId))){
			f.append(" and risends_Userid = "+userId);
		}
		if(StringUtils.hasText(String.valueOf(departId))){
			f.append(" and risends_UserdeptId = "+departId);
		}
		if(StringUtils.hasText(String.valueOf(year))){
			f.append(" and risends_Year = "+year);
		}
		return find(f,1,20000);
	}

	@Override
	public RisenDiscussion save(RisenDiscussion bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	public RisenDiscussion update(RisenDiscussion bean) {
		// TODO Auto-generated method stub
		
		String hql = "update RisenDiscussion bean set bean.risendsScore=:risendsScore,bean.risendsRemark=:risendsRemark where bean.risendsId=:risendsId";
		Query query = getSession().createQuery(hql);
		query.setParameter("risendsScore", bean.getRisendsScore());
		query.setParameter("risendsRemark", bean.getRisendsRemark());
		query.setParameter("risendsId", bean.getRisendsId());
		query.executeUpdate();
		return bean;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenDiscussion entity=super.get(id);
		if(entity!=null){
			getSession().delete(entity);
		}
	}

	@Override
	protected Class<RisenDiscussion> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenDiscussion.class;
	}

	@Override
	public Pagination getPage(int pageNo, int pageSize, Integer userId,
			Integer departId) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from RisenDiscussion where 1=1");
		if(StringUtils.hasText(String.valueOf(userId))){
			f.append(" and risends_Userid = "+userId);
		}
		if(StringUtils.hasText(String.valueOf(departId))){
			f.append(" and risends_UserdeptId = "+departId);
		}
		return find(f,pageNo,pageSize);
	}

	@Override
	public RisenDiscussion findById(Integer id) {
		// TODO Auto-generated method stub
		RisenDiscussion entity=get(id);
		return entity;
	}

}
