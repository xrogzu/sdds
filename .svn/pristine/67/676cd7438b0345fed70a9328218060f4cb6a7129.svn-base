package com.risen.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenQairesDao;
import com.risen.entity.RisenQaires;
@Repository
public class RisenQairesDao extends HibernateBaseDao<RisenQaires, Integer> implements IRisenQairesDao{
	@SuppressWarnings("unchecked")
	public Pagination getPage(String startTime,String endTime,Integer pageNo, Integer pageSize,Boolean disable,
			Integer status) {
		Finder f = Finder.create(" from RisenQaires bean where 1=1 ");
		if(startTime!=null&&startTime!="")
			f.append(" and bean.startTime>='"+startTime+"'");
		if(endTime!=null&&endTime!="")
			f.append(" and bean.endTime<='"+endTime+"'");
		if(disable!=null)
			f.append(" and bean.disabled = "+disable+" ");
		if(status!=null)
			f.append(" and bean.status = "+status+" ");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public RisenQaires save(RisenQaires bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public RisenQaires findById(Integer id) {
		RisenQaires entity = get(id);
		return entity;
	}
	@SuppressWarnings("unchecked")
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenQaires bean = super.get(id);
		if (bean != null) {
			getSession().delete(bean);
		}
	}

	@SuppressWarnings("unchecked")
	public RisenQaires update(RisenQaires bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public Pagination findList(Integer pageNo, Integer pageSize,Integer status) {
		Finder f = Finder.create(" from RisenQaires bean where 1=1 ");
		f.append(" and (bean.endTime is null or bean.endTime>now()) ");
		f.append(" and bean.disabled=true");
		if(status!=null)
			f.append(" and bean.status = "+status+" ");
		return find(f, pageNo, pageSize);
	}
	
	
	@Override
	protected Class<RisenQaires> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenQaires.class;
	}

}
