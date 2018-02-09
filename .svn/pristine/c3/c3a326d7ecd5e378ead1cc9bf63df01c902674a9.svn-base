package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenPoliticalcheckDao;
import com.risen.entity.RisenPoliticalcheck;
import com.risen.service.RisenPoliticalcheckMng;

@Service
@Transactional
public class RisenPoliticalcheckMngImpl implements RisenPoliticalcheckMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenPoliticalcheck findById(Integer id) {
		RisenPoliticalcheck entity = dao.findById(id);
		return entity;
	}

	public RisenPoliticalcheck save(RisenPoliticalcheck bean) {
		dao.save(bean);
		return bean;
	}

	public RisenPoliticalcheck update(RisenPoliticalcheck bean) {
		Updater<RisenPoliticalcheck> updater = new Updater<RisenPoliticalcheck>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenPoliticalcheck deleteById(Integer id) {
		RisenPoliticalcheck bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenPoliticalcheck[] deleteByIds(Integer[] ids) {
		RisenPoliticalcheck[] beans = new RisenPoliticalcheck[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenPoliticalcheckDao dao;

	@Autowired
	public void setDao(RisenPoliticalcheckDao dao) {
		this.dao = dao;
	}
}