package com.risen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jeecms.cms.entity.assist.CmsVoteTopic;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.ICmsAppealMailDao;
import com.risen.entity.CmsAppealMail;

@Repository
public class CmsAppealMailDaoImpl extends HibernateBaseDao<CmsAppealMail, Integer> implements ICmsAppealMailDao {

	@Override
	public Pagination getPage(int pageNo, int pageSize,Integer departId) {
		Finder f = Finder.create(" from CmsAppealMail bean where 1=1");
		if(StringUtils.hasText(String.valueOf(departId))){
			f.append(" and bean.departId="+departId);
		}
		f.append(" order by bean.cdate desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public CmsAppealMail findById(Integer appealId) {
		Finder f = Finder.create(" from CmsAppealMail bean");
		if (appealId != null) {
			f.append(" and bean.appealId=:siteId");
			f.setParam("appealId", appealId);
		}
		f.setMaxResults(1);
		f.setCacheable(true);
		return (CmsAppealMail) f.createQuery(getSession()).uniqueResult();
	}

	@Override
	public CmsAppealMail save(CmsAppealMail bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	public CmsAppealMail update(CmsAppealMail bean) {
		// TODO Auto-generated method stub
		getSession().update(bean);
		return bean;
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
	public List<CmsAppealMail> getList(Integer appealId, String name,
			String code) {
		Finder f = Finder.create(" from CmsAppealMail");
		return find(f);
	}

}
