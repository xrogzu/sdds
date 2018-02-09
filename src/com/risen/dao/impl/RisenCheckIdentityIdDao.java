package com.risen.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.risen.dao.IRisenCheckIdentityIdDao;
import com.risen.entity.RisenActive;

public class RisenCheckIdentityIdDao extends HibernateBaseDao<RisenActive, Integer> implements IRisenCheckIdentityIdDao {
	@Autowired
	private CmsUserMng cmsUserMng;
	// 判断数据库里是否存在该身份证号
	public boolean checkIsExist(String identityCardId) {
		CmsUser cmsUser = cmsUserMng.findByUserCardId(identityCardId);

		return cmsUser!=null?true:false;
	}

	@Override
	protected Class<RisenActive> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
