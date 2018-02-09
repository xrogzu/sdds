package com.jeecms.cms.manager.assist.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.assist.CmsBallotRecordDao;
import com.jeecms.cms.entity.assist.CmsBallotRecord;
import com.jeecms.cms.manager.CmsBallotRecordMng;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class CmsBallotRecordMngService implements CmsBallotRecordMng {

	@Override
	public Pagination getPage(Integer itemId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getPage(itemId, pageNo, pageSize);
	}

	@Override
	public CmsBallotRecord findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public CmsBallotRecord save(CmsBallotRecord bean) {
		// TODO Auto-generated method stub
		return dao.save(bean);
	}

	@Override
	public CmsBallotRecord update(CmsBallotRecord bean) {
		// TODO Auto-generated method stub
		return dao.update(bean);
	}

	@Override
	public CmsBallotRecord deleteById(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteById(id);
	}
	
	@Override
	public boolean existGive(String ip, String itemId) {
		// TODO Auto-generated method stub
		return dao.existGive(ip, itemId);
	}
	@Override
	public void deleteByItemId(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}
	@Autowired
	private CmsBallotRecordDao dao;
	
}
