package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenEducationcheckDao;
import com.risen.entity.RisenEducationcheck;
import com.risen.service.RisenEducationcheckMng;

@Service
@Transactional
public class RisenEducationcheckMngImpl implements RisenEducationcheckMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenEducationcheck findById(Integer id) {
		RisenEducationcheck entity = dao.findById(id);
		return entity;
	}

	public RisenEducationcheck save(RisenEducationcheck bean) {
		dao.save(bean);
		return bean;
	}

	public RisenEducationcheck update(RisenEducationcheck bean) {
		Updater<RisenEducationcheck> updater = new Updater<RisenEducationcheck>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenEducationcheck deleteById(Integer id) {
		RisenEducationcheck bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenEducationcheck[] deleteByIds(Integer[] ids) {
		RisenEducationcheck[] beans = new RisenEducationcheck[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenEducationcheckDao dao;

	@Autowired
	public void setDao(RisenEducationcheckDao dao) {
		this.dao = dao;
	}
}