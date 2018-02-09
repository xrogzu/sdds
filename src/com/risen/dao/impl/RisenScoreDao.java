package com.risen.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.util.StringUtils;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenScoreDao;
import com.risen.entity.RisenScore;

public class RisenScoreDao extends HibernateBaseDao<RisenScore, Integer> implements IRisenScoreDao {

	@Override
	public Pagination getPage(int pageNo, int pageSize, Integer userId) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from RisenScore where 1=1");
		if(StringUtils.hasText(String.valueOf(userId))){
			f.append(" and risensc_partyUserId = "+userId);
		}
		return find(f,pageNo,pageSize);
	}
	
	@Override
	public Pagination getPage(int pageNo, int pageSize, Integer userId,Integer departId) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from RisenScore bean where 1=1");
		if(userId!=null){
			f.append(" and bean.user.id = "+userId);
		}
		if(StringUtils.hasText(String.valueOf(departId))){
			f.append(" and bean.departId = "+departId);
		}
		return find(f,pageNo,pageSize);
	}

	@Override
	public RisenScore save(RisenScore score) {
		// TODO Auto-generated method stub
		getSession().save(score);
		return score;
	}

	@Override
	public RisenScore findById(Integer id) {
		// TODO Auto-generated method stub
		RisenScore score = get(id);
		return score;
	}

	@Override
	public void delete(Integer id) {
//		RisenScore score = get(id);
//		if(score!=null){
//			getSession().delete(score);
//		}
//		String hql="delete RisenScore bean and bean.id=:id where 1=1 ";
//		Query query = getSession().createQuery(hql);
//		query.setParameter(id, id);
		RisenScore entity = findById(id);
		getSession().delete(entity);
//		String sql="delete from RISEN_SCORE where ID= "+id;
//		Query query = getSession().createQuery(sql);
		
	}

	@Override
	public RisenScore update(RisenScore bean,Integer idms) {
		// TODO Auto-generated method stub
		String hql = "update RisenScore bean set bean.risenScScore=:risenScScore,bean.risenScDescribtion=:risenScDescribtion where bean.id=:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("risenScScore", bean.getRisenScScore());
    	query.setParameter("risenScDescribtion", bean.getRisenScDescribtion());
    	query.setParameter("id", idms);
    	query.executeUpdate();
		return bean;
	}

	@Override
	protected Class<RisenScore> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenScore.class;
	}

	@Override
	public boolean existScore(Integer userId, Integer departId, Integer quotaId,String risenqtYear) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from RisenScore where 1=1");
		if((StringUtils.hasText(String.valueOf(userId)))&&(StringUtils.hasText(String.valueOf(departId)))
				&&(StringUtils.hasText(String.valueOf(quotaId)))){
			f.append(" and risensc_partyUserId = "+userId);
			f.append(" and depart_id = "+departId);
			f.append(" and quota_id = "+quotaId);
			f.append(" and RISENSC_YEAR="+risenqtYear);
		}
		Pagination pagination = find(f,1,20000);
		List<RisenScore> scores = (List<RisenScore>)pagination.getList();
		if(scores.size()>0){
			return true;
		}else{
			return false;
		}
	}
	//添加年份查询
	@Override
	public Pagination getPage(int pageNo, int pageSize, Integer userId,Integer departId,String risenscYear) {
		Finder f = Finder.create(" from RisenScore where 1=1");
		if(userId!=null){
			f.append(" and risensc_partyUserId = "+userId);
		}
		if(StringUtils.hasText(String.valueOf(departId))){
			f.append(" and DEPART_ID = "+departId);
		}
		if(StringUtils.hasText(risenscYear)){
			f.append(" and RISENSC_YEAR = "+risenscYear);
		}
		return find(f,pageNo,pageSize);
	}

}
