package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenActiveDao;
import com.risen.entity.RisenActive;
import com.risen.service.RisenActiveMng;

@Service
@Transactional
public class RisenActiveMngImpl implements RisenActiveMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenActive findById(Integer id) {
		RisenActive entity = dao.findById(id);
		return entity;
	}

	public RisenActive save(RisenActive bean) {
		dao.save(bean);
		return bean;
	}

	public RisenActive update(RisenActive bean) {
		Updater<RisenActive> updater = new Updater<RisenActive>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenActive deleteById(Integer id) {
		RisenActive bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenActive[] deleteByIds(Integer[] ids) {
		RisenActive[] beans = new RisenActive[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenActiveDao dao;

	@Autowired
	public void setDao(RisenActiveDao dao) {
		this.dao = dao;
	}
}