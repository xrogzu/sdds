package com.risen.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.Query;
import org.springframework.util.StringUtils;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenPartyChangeDao;
import com.risen.entity.RisenPartyChange;

public class RisenPartyChangeDaoImpl extends HibernateBaseDao<RisenPartyChange, Integer> implements IRisenPartyChangeDao {

	@Override
	public RisenPartyChange save(RisenPartyChange bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	public Pagination getAllInfoByDepartId(int pageNo, int pageSize,
			String risenpcDeptid,String changeType) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from RisenPartyChange bean where 1=1");
		if(StringUtils.hasText(changeType)){
			f.append(" and bean.risenpcChangetype = '" + changeType + "'");
		}
		if(StringUtils.hasText(risenpcDeptid)){
			f.append(" and bean.risenpcDeptid in " + risenpcDeptid);
		}
		Pagination pagination = find(f, pageNo, pageSize);
		return pagination;
	}

	@Override
	public RisenPartyChange findById(Integer risenpcDeptid) {
		// TODO Auto-generated method stub
		RisenPartyChange entity=get(risenpcDeptid);
		return entity;
	}

	@Override
	public void delete(Integer risenpcDeptid) {
		// TODO Auto-generated method stub
		RisenPartyChange entity=super.get(risenpcDeptid);
		if(entity!=null){
			getSession().delete(entity);
		}
	}

	@Override
	public RisenPartyChange update(RisenPartyChange bean) {
		// TODO Auto-generated method stub
		getSession().update(bean);
		return bean;
	}

	@Override
	protected Class<RisenPartyChange> getEntityClass() {
		// TODO Auto-generated method stub
		return RisenPartyChange.class;
	}

	@Override
	public RisenPartyChange updeteDevparty(RisenPartyChange bean) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String hql="update RisenPartyChange bean set bean.risenpcSex=:risenpcSex,bean.risenpcIdnumber=:risenpcIdnumber,bean.risenpcBirthday=:risenpcBirthday,";
		hql = hql +"bean.risenpcNational=:risenpcNational,bean.risenpcPolicaltype=:risenpcPolicaltype,bean.risenpcAddress=:risenpcAddress,";
		hql = hql + "bean.risenpcEducation=:risenpcEducation,bean.risenpcNative=:risenpcNative,";
		hql = hql + "bean.risenpcOtherphone=:risenpcOtherphone,bean.risenpcPhone=:risenpcPhone,bean.risenpcMobile=:risenpcMobile";
		hql = hql + " where bean.risenpcId=:risenpcId";
		Query query = getSession().createQuery(hql);
		query.setParameter("risenpcSex", bean.getRisenpcSex());
		query.setParameter("risenpcIdnumber", bean.getRisenpcIdnumber());
		String birthday = sdf.format(bean.getRisenpcBirthday());
		String birthdayConvert = birthday.replaceAll("-", "/");
		try {
			query.setParameter("risenpcBirthday", format.parse(birthdayConvert));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	query.setParameter("risenpcNational", bean.getRisenpcNational());
    	query.setParameter("risenpcPolicaltype", bean.getRisenpcPolicaltype());
    	query.setParameter("risenpcAddress", bean.getRisenpcAddress());
    	query.setParameter("risenpcEducation", bean.getRisenpcEducation());
    	query.setParameter("risenpcNative", bean.getRisenpcNative());
    	query.setParameter("risenpcOtherphone", bean.getRisenpcOtherphone());
    	query.setParameter("risenpcPhone", bean.getRisenpcPhone());
    	query.setParameter("risenpcMobile", bean.getRisenpcMobile());
    	query.setParameter("risenpcId", bean.getRisenpcId());
    	query.executeUpdate();
		return bean;
	}

}
