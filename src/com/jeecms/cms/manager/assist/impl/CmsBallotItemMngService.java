package com.jeecms.cms.manager.assist.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.assist.CmsBallotItemDao;
import com.jeecms.cms.entity.assist.CmsBallotItem;
import com.jeecms.cms.manager.assist.CmsBallotItemMng;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class CmsBallotItemMngService implements CmsBallotItemMng {

	@Override
	public Pagination getPage(Integer ballotId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getPage(ballotId, pageNo, pageSize);
	}

	@Override
	public CmsBallotItem findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public CmsBallotItem save(CmsBallotItem bean) {
		// TODO Auto-generated method stub
		return dao.save(bean);
	}

	@Override
	public CmsBallotItem update(CmsBallotItem bean) {
		// TODO Auto-generated method stub
		return dao.update(bean);
	}

	@Override
	public CmsBallotItem deleteById(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteById(id);
	}
	
	@Override
	public boolean existBallotItem(String name, Integer ballotId) {
		// TODO Auto-generated method stub
		return dao.existBallotItem(name, ballotId);
	}
	
	@Override
	public void deleteAllItem(Integer itemId) {
		// TODO Auto-generated method stub
		dao.deleteAllItem(itemId);
	}
	
	@Autowired
	private CmsBallotItemDao dao;

	
	
}
