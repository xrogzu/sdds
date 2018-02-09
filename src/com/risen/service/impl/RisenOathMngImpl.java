package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenOathDao;
import com.risen.entity.RisenOath;
import com.risen.service.RisenOathMng;

@Service
@Transactional
public class RisenOathMngImpl implements RisenOathMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenOath findById(Integer id) {
		RisenOath entity = dao.findById(id);
		return entity;
	}

	public RisenOath save(RisenOath bean) {
		dao.save(bean);
		return bean;
	}

	public RisenOath update(RisenOath bean) {
		Updater<RisenOath> updater = new Updater<RisenOath>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenOath deleteById(Integer id) {
		RisenOath bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenOath[] deleteByIds(Integer[] ids) {
		RisenOath[] beans = new RisenOath[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenOathDao dao;

	@Autowired
	public void setDao(RisenOathDao dao) {
		this.dao = dao;
	}
}