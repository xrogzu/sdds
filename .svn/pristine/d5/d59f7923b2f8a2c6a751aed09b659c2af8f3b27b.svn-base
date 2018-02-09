package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenContactDao;
import com.risen.entity.RisenContact;
import com.risen.service.RisenContactMng;

@Service
@Transactional
public class RisenContactMngImpl implements RisenContactMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenContact findById(Integer id) {
		RisenContact entity = dao.findById(id);
		return entity;
	}

	public RisenContact save(RisenContact bean) {
		dao.save(bean);
		return bean;
	}

	public RisenContact update(RisenContact bean) {
		Updater<RisenContact> updater = new Updater<RisenContact>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenContact deleteById(Integer id) {
		RisenContact bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenContact[] deleteByIds(Integer[] ids) {
		RisenContact[] beans = new RisenContact[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenContactDao dao;

	@Autowired
	public void setDao(RisenContactDao dao) {
		this.dao = dao;
	}
}