package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenPartyrecordDao;
import com.risen.entity.RisenPartyrecord;
import com.risen.service.RisenPartyrecordMng;

@Service
@Transactional
public class RisenPartyrecordMngImpl implements RisenPartyrecordMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenPartyrecord findById(Integer id) {
		RisenPartyrecord entity = dao.findById(id);
		return entity;
	}

	public RisenPartyrecord save(RisenPartyrecord bean) {
		dao.save(bean);
		return bean;
	}

	public RisenPartyrecord update(RisenPartyrecord bean) {
		Updater<RisenPartyrecord> updater = new Updater<RisenPartyrecord>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenPartyrecord deleteById(Integer id) {
		RisenPartyrecord bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenPartyrecord[] deleteByIds(Integer[] ids) {
		RisenPartyrecord[] beans = new RisenPartyrecord[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenPartyrecordDao dao;

	@Autowired
	public void setDao(RisenPartyrecordDao dao) {
		this.dao = dao;
	}
}