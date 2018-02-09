package com.risen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteQuesContentDao;
import com.risen.entity.RisenVoteQuesContent;
@Repository
public class RisenVoteQuesContentDao extends HibernateBaseDao<RisenVoteQuesContent,Integer> 
			implements IRisenVoteQuesContentDao{
	@SuppressWarnings("unchecked")
	public Pagination getPage(Integer pageNo, Integer pageSize,Integer quesId) {
		Finder f = Finder.create(" from RisenVoteQuesContent bean where 1=1 ");
		if(quesId!=null)
			f.append(" and bean.ques.id = "+quesId+" ");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public RisenVoteQuesContent save(RisenVoteQuesContent bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public RisenVoteQuesContent findById(Integer id) {
		RisenVoteQuesContent entity = get(id);
		return entity;
	}
	@SuppressWarnings("unchecked")
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenVoteQuesContent bean = super.get(id);
		if (bean != null) {
			getSession().delete(bean);
		}
	}

	@SuppressWarnings("unchecked")
	public RisenVoteQuesContent update(RisenVoteQuesContent bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public List<RisenVoteQuesContent> findListByQues(Integer quesId){
		Finder f = Finder.create(" from RisenVoteQuesContent bean where bean.ques.id="+quesId);
		f.append(" order by bean.id desc");
		return find(f);
	}
	@Override
	protected Class<RisenVoteQuesContent> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenVoteQuesContent.class;
	}

}
