package com.risen.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenQairesDetailDao;
import com.risen.entity.RisenQairesDetail;

public class RisenQairesDetailDao extends HibernateBaseDao<RisenQairesDetail, Integer> 
		implements IRisenQairesDetailDao{
	@SuppressWarnings("unchecked")
	public Pagination getPage(Integer recordId,Integer pageNo,Integer pageSize){
		Finder f = Finder.create(" from RisenQairesDetail bean where 1=1 ");
		if(recordId!=null)
			f.append(" and bean.record.id = "+recordId);
		f.append(" order by bean.id asc");
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public RisenQairesDetail save(RisenQairesDetail bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public void deleteByRecord(Integer recordId){
		String hql = "delete from RisenQairesDetail bean where bean.record.id="+recordId;
		getSession().createQuery(hql).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public void deleteByTopic(Integer topicId){
		String hql = "delete from RisenQairesDetail bean where bean.topic.id="+topicId;
		getSession().createQuery(hql).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public RisenQairesDetail findById(Integer id) {
		RisenQairesDetail entity = get(id);
		return entity;
	}
	@Override
	protected Class<RisenQairesDetail> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenQairesDetail.class;
	}
	

}
