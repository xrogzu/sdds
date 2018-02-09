package com.jeecms.cms.manager.assist.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.assist.CmsAppealMailDao;
import com.jeecms.cms.entity.assist.CmsAppealMail;
import com.jeecms.cms.manager.assist.CmsAppealMailMng;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class CmsAppealMailMngImpl implements CmsAppealMailMng {

	@Override
	public Pagination getPage(Integer departId,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getPage(departId,pageNo, pageSize);
	}

	@Override
	public CmsAppealMail getReplay(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsAppealMail findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public CmsAppealMail save(CmsAppealMail bean) {
		// TODO Auto-generated method stub
		return dao.save(bean);
	}

	@Override
	public int update(Integer appealId) {
		// TODO Auto-generated method stub
		return dao.update(appealId);
	}

	@Override
	public CmsAppealMail[] deleteByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		CmsAppealMail[] mails = new CmsAppealMail[ids.length];
		for(int i = 0;i<ids.length;i++){
			mails[i] = deleteById(ids[i]);
		}
		return mails;
	}
	
	@Override
	public CmsAppealMail deleteById(Integer ids) {
		// TODO Auto-generated method stub
		return dao.deleteById(ids);
	}
	@Override
	public CmsAppealMail fingByCode(String code) {
		// TODO Auto-generated method stub
		return dao.fingByCode(code);
	}
	@Autowired
	private CmsAppealMailDao dao;
	
	
}
