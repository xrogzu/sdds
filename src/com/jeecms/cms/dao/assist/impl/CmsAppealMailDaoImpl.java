package com.jeecms.cms.dao.assist.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jeecms.cms.dao.assist.CmsAppealMailDao;
import com.jeecms.cms.entity.assist.CmsAppealMail;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenTargetDetail;

@Repository
public class CmsAppealMailDaoImpl extends HibernateBaseDao<CmsAppealMail, Integer> implements CmsAppealMailDao {

	@Override
	public Pagination getPage(Integer departId,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create(" from CmsAppealMail where 1=1");
		if(StringUtils.hasText(String.valueOf(departId))){
			f.append(" and departId = "+departId);
		}
		f.append(" order by cdate desc");
		return find(f,pageNo,pageSize);
	}

	@Override
	public CmsAppealMail findById(Integer id) {
		// TODO Auto-generated method stub
		CmsAppealMail bean = get(id);
		return bean;
	}

	@Override
	public CmsAppealMail save(CmsAppealMail bean) {
		// TODO Auto-generated method stub
		String code = getCode();
		for(int i = 0;checkIsExist(code);i++){
			code = getCode();
		}
		bean.setCode(code);
		bean.setCdate(new Date());
		getSession().save(bean);
		return bean;
	}

	@Override
	public int update(Integer appealId) {
		// TODO Auto-generated method stub

		String hql = "update CmsAppealMail bean set bean.replay=1 where bean.appealId=:appealId";
		Query query = getSession().createQuery(hql);
		query.setParameter("appealId", appealId);
		return query.executeUpdate();
	}

	@Override
	public CmsAppealMail deleteById(Integer id) {
		// TODO Auto-generated method stub
		CmsAppealMail entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CmsAppealMail> getEntityClass() {
		// TODO Auto-generated method stub
		return CmsAppealMail.class;
	}

	@Override
	public boolean checkIsExist(String code) {
		// TODO Auto-generated method stub
		String sql = "select * from jc_appeal_mail where code = '"+code+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addEntity(CmsAppealMail.class);
		List<CmsAppealMail> mails = query.list();
		if(mails.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public String getCode(){
		String code = "";
		String str = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"; 
		String[] str2 = str.split(",");
		int strLength = str2.length-1;
		Random rand = new Random();
		int index = 0; 
		for(int i = 0;i<4;i++){
			index = rand.nextInt(strLength);//在0到str2.length-1生成一个伪随机数赋值给index  
			code += str2[index];
		}
		return code;
	}

	@Override
	public CmsAppealMail fingByCode(String code) {
		// TODO Auto-generated method stub
		CmsAppealMail mail = null;
		String sql = "select * from jc_appeal_mail where code = '"+code+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addEntity(CmsAppealMail.class);
		List<CmsAppealMail> list = query.list();
		if(list.size()>0){
			mail = list.get(0);
		}
		return mail;
	}
}
