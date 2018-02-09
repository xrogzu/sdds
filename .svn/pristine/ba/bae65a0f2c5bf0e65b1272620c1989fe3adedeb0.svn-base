package com.risen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteQuesDao;
import com.risen.entity.RisenVoteQues;
@Repository
public class RisenVoteQuesDao extends HibernateBaseDao<RisenVoteQues, Integer> 
		implements IRisenVoteQuesDao{
	@SuppressWarnings("unchecked")
	public Pagination getPage(Integer pageNo, Integer pageSize,Integer voteId) {
		Finder f = Finder.create(" from RisenVoteQues bean where 1=1 ");
		if(voteId!=null)
			f.append(" and bean.vote.id = "+voteId+" ");
		f.append(" order by bean.quesSort asc, bean.id asc");
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public RisenVoteQues save(RisenVoteQues voteQues){
		getSession().save(voteQues);
		return voteQues;
	}
	@SuppressWarnings("unchecked")
	public RisenVoteQues findById(Integer id) {
		RisenVoteQues entity = get(id);
		return entity;
	}
	@SuppressWarnings("unchecked")
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenVoteQues bean = super.get(id);
		if (bean != null) {
			getSession().delete(bean);
		}
	}
	@SuppressWarnings("unchecked")
	public RisenVoteQues update(RisenVoteQues bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public List<RisenVoteQues> findListByVote(Integer voteId,Integer quesNature){
		Finder f = Finder.create(" from RisenVoteQues bean where bean.vote.id="+voteId);
		if(quesNature!=null)
			f.append(" and bean.quesNature="+quesNature);
		f.append(" order by bean.quesSort asc,bean.id asc");
		return find(f);
	}	
	
	public void sort(Integer id,Integer sort){
		getSession().createQuery(" update RisenVoteQues t set t.quesSort ="+sort+" where id = "+id).executeUpdate(); 
	}
	@Override
	protected Class<RisenVoteQues> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenVoteQues.class;
	}
	

}
