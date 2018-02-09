package com.risen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteRecordDetailDao;
import com.risen.entity.RisenVoteRecordDetail;
@Repository
public class RisenVoteRecordDetailDao extends HibernateBaseDao<RisenVoteRecordDetail,Integer> 
			implements IRisenVoteRecordDetailDao{
	@SuppressWarnings("unchecked")
	public Pagination getPage(Integer recordId,Integer pageNo, Integer pageSize) {
		Finder f = Finder.create(" from RisenVoteRecordDetail bean where 1=1 ");
		
		if(recordId!=null)
			f.append(" and bean.record.id = "+recordId+" ");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public RisenVoteRecordDetail save(RisenVoteRecordDetail bean) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public RisenVoteRecordDetail findById(Integer id) {
		RisenVoteRecordDetail entity = get(id);
		return entity;
	}
	@SuppressWarnings("unchecked")
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenVoteRecordDetail bean = super.get(id);
		if (bean != null) {
			getSession().delete(bean);
		}
	}

	@SuppressWarnings("unchecked")
	public RisenVoteRecordDetail update(RisenVoteRecordDetail bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}
	@SuppressWarnings("unchecked")
	public List<RisenVoteRecordDetail> findListByRecord(Integer recordId){
		Finder f = Finder.create(" from RisenVoteRecordDetail bean where bean.record.id="+recordId);
		f.append(" order by bean.id asc");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public void deleteByRecord(Integer decordId){
		String hql = "delete from RisenVoteRecordDetail bean where bean.record.id="+decordId;
		getSession().createQuery(hql).executeUpdate();
	}
	@Override
	protected Class<RisenVoteRecordDetail> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenVoteRecordDetail.class;
	}
	

}
