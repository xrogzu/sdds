package com.risen.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteItemDao;
import com.risen.entity.RisenVoteItem;
@Repository
public class RisenVoteItemDao extends HibernateBaseDao<RisenVoteItem, Integer> 
		implements IRisenVoteItemDao{
	
	@SuppressWarnings("unchecked")
	public Pagination getPage(Integer voteId,Integer quesId,Integer pageNo, Integer pageSize) {
		Finder f = Finder.create(" from RisenVoteItem bean where 1=1 ");
		if(voteId!=null)
			f.append(" and bean.vote.id="+voteId);
		if(quesId!=null)
			f.append(" and bean.ques.id = "+quesId+" ");
		f.append(" order by bean.itemState asc, bean.id asc");
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public RisenVoteItem save(RisenVoteItem item){
		getSession().saveOrUpdate(item);
		return item;
	}
	
	@SuppressWarnings("unchecked")
	public RisenVoteItem findById(Integer id){
		RisenVoteItem item=get(id);
		return item;
	}
	
	@SuppressWarnings("unchecked")
	public RisenVoteItem update(RisenVoteItem item){
		getSession().saveOrUpdate(item);
		return item;
	}
	@SuppressWarnings("unchecked")
	public void delete(Integer id){
		RisenVoteItem entity=get(id);
		if(entity!=null){
			getSession().delete(entity);
		}
	}
	@SuppressWarnings("unchecked")
	public void sort(Integer id,Integer sort){
		getSession().createQuery(" update RisenVoteItem t set t.itemState ="+sort+" where id = "+id).executeUpdate(); 
	}
	@SuppressWarnings("unchecked")
	public List<RisenVoteItem> findListByVote(Integer voteId){
		Finder f = Finder.create(" from RisenVoteItem bean where bean.vote.id="+voteId);
		f.append(" order by bean.itemState asc,bean.id asc");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<RisenVoteItem> findListByQues(Integer quesId){
		Finder f = Finder.create(" from RisenVoteItem bean where bean.ques.id="+quesId);
		f.append(" order by bean.itemState asc,bean.id desc");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public void deleteByQues(Integer quesId){
		String hql = "delete from RisenVoteItem bean where bean.ques.id="+quesId;
		getSession().createQuery(hql).executeUpdate();
	}
	@Override
	protected Class<RisenVoteItem> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenVoteItem.class;
	}
	
	

}
