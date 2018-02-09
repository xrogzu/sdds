package com.risen.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteRecordDao;
import com.risen.entity.RisenVoteRecord;
@Repository
public class RisenVoteRecordDao extends HibernateBaseDao<RisenVoteRecord, Integer> 
			implements IRisenVoteRecordDao{
	@SuppressWarnings("unchecked")
	public Pagination getPage(Integer voteId,Integer pageNo, Integer pageSize) {
		Finder f = Finder.create(" from RisenVoteRecord bean where 1=1 ");
		if(voteId!=null)
			f.append(" and bean.vote.id="+voteId);
		
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public RisenVoteRecord save(RisenVoteRecord bean) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public RisenVoteRecord findById(Integer id) {
		RisenVoteRecord entity = get(id);
		return entity;
	}
	@SuppressWarnings("unchecked")
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenVoteRecord bean = super.get(id);
		if (bean != null) {
			getSession().delete(bean);
		}
	}
	@SuppressWarnings("unchecked")
	public void deleteByVote(Integer voteId){
		String hql = "delete from RisenVoteRecord bean where bean.vote.id="+voteId;
		getSession().createQuery(hql).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public RisenVoteRecord update(RisenVoteRecord bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public List<RisenVoteRecord> findListByVote(Integer voteId,String recordPhone){
		Finder f = Finder.create(" from RisenVoteRecord bean where bean.vote.id="+voteId);
		if(!StringUtils.isBlank(recordPhone)){
			f.append(" and bean.recordPhone = '"+recordPhone+"'");
		}
		f.append(" order by bean.id desc  ");
		return find(f);
	}	
	

	@Override
	protected Class<RisenVoteRecord> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenVoteRecord.class;
	}
	

}
