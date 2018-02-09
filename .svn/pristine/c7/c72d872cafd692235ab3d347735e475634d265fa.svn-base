package com.risen.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.core.entity.CmsDepartment;
import com.risen.dao.IRisenChangeremindrecordDao;
import com.risen.entity.RisenChangeremindrecord;

@Repository
public class RisenChangeremindrecordDaoImpl extends HibernateBaseDao<RisenChangeremindrecord, Integer> implements IRisenChangeremindrecordDao{

	@Override
	protected Class<RisenChangeremindrecord> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenChangeremindrecord.class;
	}

	@Override
	public RisenChangeremindrecord save(RisenChangeremindrecord model) {
		// TODO Auto-generated method stub
		getSession().save(model);
		return model;
	}

	@Override
	public RisenChangeremindrecord getByOrgid(Integer orgid){
		return (RisenChangeremindrecord)getSession().createQuery(" from RisenChangeremindrecord bean where bean.id=:id").setParameter("id", orgid).uniqueResult();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		RisenChangeremindrecord record = super.get(id);
		if(record!=null){
			getSession().delete(record);
		}
	}

	@Override
	public String getOrgNameById(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from RisenChangeremindrecord bean where 1=1 ";
		if(id != null){
			hql += (" and id=:id");
			Query query = getSession().createQuery(hql).setParameter("id", id);
			List<RisenChangeremindrecord> records = query.list();
			if(records!=null && records.size()>0){
				return records.get(0).getSddsccOrgname();
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
}
