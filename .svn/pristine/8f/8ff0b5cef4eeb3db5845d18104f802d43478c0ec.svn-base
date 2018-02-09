package com.jeecms.cms.dao.assist.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jeecms.cms.dao.assist.ContentFrontDao;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.common.hibernate4.HibernateBaseDao;

@Repository
public class ContentFrontDaoImpl extends HibernateBaseDao<Content, Integer> implements ContentFrontDao {

	@Override
	public List<Content> getListByName(String name,Integer siteId) {
		// TODO Auto-generated method stub
		String sql = "select * from jc_content where 1=1";
		if(StringUtils.hasText(name)){
			sql += " and content_id in ( ";
			sql += "select content_id from jc_content_ext where  title like '%"+ name +"%' or description like '%"+ name +"%')";
		}
		if(siteId!=null){
			//兼容之前存在的数据
			sql += " and (site_id = "+siteId+" or site_id = 1)";
		}
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(Content.class);
		List<Content> contents = query.list();
		return contents;
	}

	@Override
	protected Class<Content> getEntityClass() {
		// TODO Auto-generated method stub
		return Content.class;
	}

}
