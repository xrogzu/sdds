package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenPartyapplicationDao;
import com.risen.entity.RisenPartyapplication;
import com.risen.service.RisenPartyapplicationMng;

@Service
@Transactional
public class RisenPartyapplicationMngImpl implements RisenPartyapplicationMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenPartyapplication findById(Integer id) {
		RisenPartyapplication entity = dao.findById(id);
		return entity;
	}

	public RisenPartyapplication save(RisenPartyapplication bean) {
		dao.save(bean);
		return bean;
	}

	public RisenPartyapplication update(RisenPartyapplication bean) {
		Updater<RisenPartyapplication> updater = new Updater<RisenPartyapplication>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenPartyapplication deleteById(Integer id) {
		RisenPartyapplication bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenPartyapplication[] deleteByIds(Integer[] ids) {
		RisenPartyapplication[] beans = new RisenPartyapplication[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenPartyapplicationDao dao;

	@Autowired
	public void setDao(RisenPartyapplicationDao dao) {
		this.dao = dao;
	}
}