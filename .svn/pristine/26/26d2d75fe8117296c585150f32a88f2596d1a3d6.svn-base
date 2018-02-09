package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.ICmsAppealMailDao;
import com.risen.dao.ICmsAppealMailReplayDao;
import com.risen.entity.CmsAppealMail;
import com.risen.entity.CmsAppealMailReplay;
import com.risen.service.CmsAppealMailMng;

@Service
@Transactional
public class CmsAppealMailMngImpl implements CmsAppealMailMng {

	@Override
	public Pagination getPage(int pageNo, int pageSize,Integer departId) {
		// TODO Auto-generated method stub
		Pagination pagination = dao.getPage(pageNo, pageSize,departId);
		
		return pagination;
	}

	@Override
	public CmsAppealMail getReplay(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsAppealMail findById(Integer appealId) {
		// TODO Auto-generated method stub
		return dao.findById(appealId);
	}

	@Override
	public CmsAppealMail save(CmsAppealMail bean) {
		// TODO Auto-generated method stub
		return dao.save(bean);
	}

	@Override
	public CmsAppealMail update(CmsAppealMail bean) {
		// TODO Auto-generated method stub
		return dao.update(bean);
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
	public List<CmsAppealMail> getList(Integer appealId, String name,
			String title, String content, String code) {
		// TODO Auto-generated method stub
		return dao.getList(appealId, name, code);
	}
	@Autowired
	private ICmsAppealMailDao dao;
	@Autowired
	private ICmsAppealMailReplayDao replayDao;
}
