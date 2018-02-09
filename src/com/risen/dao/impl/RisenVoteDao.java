package com.risen.dao.impl;


import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteDao;
import com.risen.entity.RisenVote;
@Repository
public class RisenVoteDao extends HibernateBaseDao<RisenVote, Integer> 
		implements IRisenVoteDao{
	//投票展示页面
	@SuppressWarnings("unchecked")
	public Pagination getPage(String startTime,String endTime,Integer pageNo, Integer pageSize,
			Boolean status) {
		Finder f = Finder.create(" from RisenVote bean where 1=1 ");
		if(startTime!=null&&startTime!="")
			f.append(" and ( bean.startTime>='"+startTime+"' or bean.startTime is null)");
		if(endTime!=null&&endTime!="")
			f.append(" and ( bean.endTime<='"+endTime+"' or bean.endTime is null )");
		if(status!=null)
			f.append(" and bean.status = "+status+" ");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	//投票的保存
	@SuppressWarnings("unchecked")
	public RisenVote save(RisenVote bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}
	//根据id来查找
	@SuppressWarnings("unchecked")
	public RisenVote findById(Integer id){
		RisenVote entity=get(id);
		return entity;
	}
	//修改的方法
	@SuppressWarnings("unchecked")
	public RisenVote update(RisenVote bean){
		getSession().saveOrUpdate(bean);
		return bean;
	}
	//删除的方法
	@SuppressWarnings("unchecked")
	public void delete(Integer id){
		RisenVote entity=super.get(id);
		if(entity!=null){
			getSession().delete(entity);
		}
	}
	@Override
	protected Class<RisenVote> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenVote.class;
	}
	

}
