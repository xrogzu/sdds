package com.jeecms.cms.manager.assist.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.assist.CmsAppealMailReplayDao;
import com.jeecms.cms.manager.assist.CmsAppealMailReplayMng;
import com.risen.entity.CmsAppealMailReplay;

@Service
@Transactional
public class CmsAppealMailReplayMngImpl implements CmsAppealMailReplayMng {

	@Override
	public CmsAppealMailReplay findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public CmsAppealMailReplay save(CmsAppealMailReplay bean) {
		// TODO Auto-generated method stub
		return dao.save(bean);
	}
	@Autowired
	private CmsAppealMailReplayDao dao;

}
