package com.jeecms.cms.dao.assist.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jeecms.cms.dao.assist.CmsBallotDao;
import com.jeecms.cms.entity.assist.CmsBallot;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class CmsBallotDaoImpl extends HibernateBaseDao<CmsBallot, Integer> implements CmsBallotDao {

	@Override
	public Pagination getPage(Integer departId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from CmsBallot where isDisabled = 1 and endTime>=to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd')");
		if(!StringUtils.isEmpty(departId)){
			f.append(" and depart_id = "+departId);
		}
		return find(f,pageNo,pageSize);
	}
	
	@Override
	public Pagination getPages(Integer departId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from CmsBallot where 1=1");
		if(!StringUtils.isEmpty(departId)){
			f.append(" and depart_id = "+departId + " order by startTime desc");
		}
		return find(f,pageNo,pageSize);
	}

	@Override
	public CmsBallot findById(Integer id) {
		// TODO Auto-generated method stub
		CmsBallot bean = get(id);
		return bean;
	}

	@Override
	public CmsBallot deleteById(Integer id) {
		// TODO Auto-generated method stub
		CmsBallot entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public CmsBallot save(CmsBallot bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<CmsBallot> getEntityClass() {
		// TODO Auto-generated method stub
		return CmsBallot.class;
	}
	
	@Override
	public boolean existBallot(String ballotName,Integer departId) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(" from CmsBallot where 1=1");
		if(!StringUtils.isEmpty(departId)){
			sb.append(" and depart_id = "+departId);
		}
		if(!StringUtils.isEmpty(ballotName)){
			sb.append(" and title = '"+ballotName+"'");
		}
		List<CmsBallot> list = getSession().createQuery(sb.toString()).list();
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public CmsBallot update(CmsBallot bean) {
		// TODO Auto-generated method stub
		String hql = "";
		if(StringUtils.hasText(bean.getBackgroundImg())){
			hql="update CmsBallot bean set bean.title=:title,bean.startTime=:startTime,bean.endTime=:endTime,bean.introduction=:introduction,bean.multiSelect=:multiSelect,bean.backgroundImg=:backgroundImg where bean.ballotId=:ballotId";
		}else{
			hql="update CmsBallot bean set bean.title=:title,bean.startTime=:startTime,bean.endTime=:endTime,bean.introduction=:introduction,bean.multiSelect=:multiSelect where bean.ballotId=:ballotId";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter("startTime", bean.getStartTime());
		query.setParameter("title", bean.getTitle());
		query.setParameter("endTime", bean.getEndTime());
    	query.setParameter("introduction", bean.getIntroduction());
    	query.setParameter("ballotId", bean.getBallotId());
    	query.setParameter("multiSelect", bean.getMultiSelect());
    	if(StringUtils.hasText(bean.getBackgroundImg())){
    		query.setParameter("backgroundImg", bean.getBackgroundImg());
		}
    	query.executeUpdate();
		return bean;
	}

	

}
