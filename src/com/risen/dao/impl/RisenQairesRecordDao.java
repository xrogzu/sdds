package com.risen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.risen.dao.IRisenQairesRecordDao;
import com.risen.entity.RisenQairesRecord;
@Repository
public class RisenQairesRecordDao extends HibernateBaseDao<RisenQairesRecord, Integer> 
		implements IRisenQairesRecordDao{
	@SuppressWarnings("unchecked")
	public Pagination getPage(Integer qairesId,String userName,String startDate,String endDate,Integer pageNo,Integer pageSize){
		Finder f = Finder.create(" from RisenQairesRecord bean where 1=1 ");
		if(qairesId!=null)
			f.append(" and bean.qaires.id = "+qairesId);
		if(startDate!=null&&startDate!="")
			f.append(" and bean.recordTime>='"+startDate+"'");
		if(endDate!=null&&endDate!="")
			f.append(" and bean.recordTime<='"+endDate+"'");
		if(userName!=null)
			f.append(" and bean.user.username like '%"+userName+"%' ");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}	
	@SuppressWarnings("unchecked")
	public List<RisenQairesRecord> getList(Integer qairesId,String userName,String startDate,String endDate){
		Finder f = Finder.create(" from RisenQairesRecord bean where 1=1 ");
		if(qairesId!=null)
			f.append(" and bean.qaires.id = "+qairesId);
		if(startDate!=null&&startDate!="")
			f.append(" and bean.recordTime>='"+startDate+"'");
		if(endDate!=null&&endDate!="")
			f.append(" and bean.recordTime<='"+endDate+"'");
		if(userName!=null)
			f.append(" and bean.user.username like '%"+userName+"%' ");
		f.append(" order by bean.recordTime desc,bean.id desc");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<RisenQairesRecord> findList(Integer qairesId,CmsUser user){
		Finder f = Finder.create(" from RsQairesRecord bean where 1=1 ");
		if(qairesId!=null)
			f.append(" and bean.qaires.id = "+qairesId);
		if(user!=null)
			f.append(" and bean.user.username ='"+user.getUsername()+"' ");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public void deleteByQaires(Integer qairesId){
		String hql = "delete from RisenQairesRecord bean where bean.qaires.id="+qairesId;
		getSession().createQuery(hql).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public void deleteByUser(Integer userId){
		String hql = "delete from RisenQairesRecord bean where bean.user.id="+userId;
		getSession().createQuery(hql).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public RisenQairesRecord save(RisenQairesRecord bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public RisenQairesRecord findById(Integer id) {
		RisenQairesRecord entity = get(id);
		return entity;
	}
	@SuppressWarnings("unchecked")
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenQairesRecord bean = super.get(id);
		if (bean != null) {
			getSession().delete(bean);
		}
	}

	@SuppressWarnings("unchecked")
	public RisenQairesRecord update(RisenQairesRecord bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}
	@Override
	protected Class<RisenQairesRecord> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenQairesRecord.class;
	}
	

}
