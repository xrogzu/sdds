package com.risen.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenQairesContentDao;
import com.risen.entity.RisenQairesContent;
@Repository
public class RisenQairesContentDao extends HibernateBaseDao<RisenQairesContent, Integer> 
			implements IRisenQairesContentDao{
	@SuppressWarnings("unchecked")
	public Pagination getPage(Integer topicId,Integer pageNo, Integer pageSize){
		Finder f = Finder.create(" from RisenQairesContent bean where 1=1 ");
		if(topicId!=null)
			f.append(" and bean.topic.id = "+topicId);
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public RisenQairesContent save(RisenQairesContent bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public void deleteByRecord(Integer recordId){
		String hql = "delete from RisenQairesContent bean where bean.record.id="+recordId;
		getSession().createQuery(hql).executeUpdate();
	}
	
	
	@Override
	protected Class<RisenQairesContent> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenQairesContent.class;
	}
	
	

}
