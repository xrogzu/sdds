package com.risen.dao.impl;

import java.util.List;

import org.apache.shiro.util.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenQuotaDao;
import com.risen.entity.RisenQuota;

public class RisenQuotaDao extends HibernateBaseDao<RisenQuota, Integer> implements IRisenQuotaDao {

	@Override
	public Pagination getPage(int pageNo, int pageSize, Integer deptId,String risenType) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from RisenQuota where 1=1");
		if(StringUtils.hasText(String.valueOf(deptId))){
			f.append(" and risenqt_departId = "+deptId);
		}
		if(StringUtils.hasText(risenType)){
			f.append(" and risenqt_type = "+risenType);
		}
		f.append(" order by risenqt_type,risenqt_addtype,id");
		return find(f,pageNo,pageSize);
	}

	@Override
	public RisenQuota save(RisenQuota quota) {
		// TODO Auto-generated method stub
		getSession().save(quota);
		return quota;
	}

	@Override
	public RisenQuota findById(Integer id) {
		// TODO Auto-generated method stub
		RisenQuota bean = get(id);
		return bean;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenQuota bean = get(id);
		if(bean!=null){
			getSession().delete(bean);
		}
	}

	@Override
	public RisenQuota update(RisenQuota bean) {
		// TODO Auto-generated method stub
		String hql = "update RisenQuota bean set bean.risenqtName=:risenqtName,bean.risenqtScore=:risenqtScore where bean.id=:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("risenqtName", bean.getRisenqtName());
    	query.setParameter("risenqtScore", bean.getRisenqtScore());
    	query.setParameter("id", bean.getId());
    	query.executeUpdate();
		return bean;
	}

	@Override
	protected Class<RisenQuota> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenQuota.class;
	}

	@Override
	public boolean existQuota(String quotaName, Integer departId,String risenType,String risenAddType) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from RisenQuota where 1=1");
		if((StringUtils.hasText(quotaName)) && (StringUtils.hasText(String.valueOf(departId)))){
			f.append(" and risenqt_name = '"+quotaName+"'");
			f.append(" and risenqt_departId = "+departId);
		}
		if(StringUtils.hasText(risenType)){
			f.append(" and risenqt_type = "+risenType);
		}
		if(StringUtils.hasText(risenAddType)){
			f.append(" and risenqt_addType = "+risenAddType);
		}
		Pagination pagination = find(f,1,20000);
		List<RisenQuota> quotas = (List<RisenQuota>)pagination.getList();
		if(quotas.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Pagination getPage(int pageNo, int pageSize, Integer deptId,
		String risenType, Integer id) {
			Finder f = Finder.create(" from RisenScore where 1=1");
			f.append(" and id = "+id);
		return find(f,pageNo,pageSize);
	}

	@Override
	public RisenQuota score(Integer id) {
		String sql="select * from risen_quota where 1=1";
		if(StringUtils.hasText(String.valueOf(id))){
			sql+=" and id="+id;
		}
//		List<RisenQuota> list = getSession().createSQLQuery(sql).list();
//		return list.get(0);
//		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
//		return sqlQuery.list();
		return (RisenQuota) getSession().createSQLQuery(sql).addEntity(RisenQuota.class).uniqueResult();

	}

}
