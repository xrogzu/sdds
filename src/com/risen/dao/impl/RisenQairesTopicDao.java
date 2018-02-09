package com.risen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenQairesTopicDao;
import com.risen.entity.RisenQairesTopic;
@Repository
public class RisenQairesTopicDao extends HibernateBaseDao<RisenQairesTopic, Integer> implements IRisenQairesTopicDao{
	@SuppressWarnings("unchecked")
	public Pagination getPage(Integer qairesId,Integer pageNo, Integer pageSize){
		Finder f = Finder.create(" from RisenQairesTopic bean where 1=1 ");
		if(qairesId!=null){
			f.append(" and bean.qaires.id ="+qairesId);
		}
		f.append(" order by bean.torderBy asc,bean.id desc");
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public List<RisenQairesTopic> finList(Integer qairesId){
		Finder f = Finder.create(" from RisenQairesTopic bean where bean.qaires.id="+qairesId);
		f.append(" order by bean.torderBy asc,bean.type asc,bean.id asc");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public RisenQairesTopic save(RisenQairesTopic bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public RisenQairesTopic findById(Integer id) {
		RisenQairesTopic entity = get(id);
		return entity;
	}
	@SuppressWarnings("unchecked")
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenQairesTopic bean = super.get(id);
		if (bean != null) {
			getSession().delete(bean);
		}
	}
	@SuppressWarnings("unchecked")
	public void sort(Integer id,Integer sort){
		getSession().createQuery(" update RisenQairesTopic t set t.torderBy ="+sort+" where id = "+id).executeUpdate(); 
	}

	@SuppressWarnings("unchecked")
	public RisenQairesTopic update(RisenQairesTopic bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}
	
	
	
	@Override
	protected Class<RisenQairesTopic> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenQairesTopic.class;
	}
	

}
