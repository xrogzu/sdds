package com.risen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenQairesItemDao;
import com.risen.entity.RisenQairesItem;
@Repository
public class RisenQairesItemDao extends HibernateBaseDao<RisenQairesItem, Integer> implements IRisenQairesItemDao{
	@SuppressWarnings("unchecked")
	public List<RisenQairesItem> findList(Integer topicId){
		Finder f = Finder.create(" from RisenQairesItem bean where bean.qairesTopic.id="+topicId);
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public Pagination getPage(Integer topicId,Integer pageNo, Integer pageSize){
		Finder f = Finder.create(" from RisenQairesItem bean where 1=1 ");
		if(topicId!=null){
			f.append(" and bean.qairesTopic.id ="+topicId);
		}
		f.append(" order by bean.priority asc, bean.id desc");
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public RisenQairesItem save(RisenQairesItem bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public void deleteByTopic(Integer topicId){
		String hql = "delete from RisenQairesItem bean where bean.qairesTopic.id="+topicId;
		getSession().createQuery(hql).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public RisenQairesItem findById(Integer id) {
		RisenQairesItem entity = get(id);
		return entity;
	}
	@SuppressWarnings("unchecked")
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenQairesItem bean = super.get(id);
		if (bean != null) {
			getSession().delete(bean);
		}
	}

	@SuppressWarnings("unchecked")
	public RisenQairesItem update(RisenQairesItem bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}
	
	@Override
	protected Class<RisenQairesItem> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenQairesItem.class;
	}

}
