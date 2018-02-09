package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenPartyintroducerDao;
import com.risen.entity.RisenPartyintroducer;
import com.risen.service.RisenPartyintroducerMng;

@Service
@Transactional
public class RisenPartyintroducerMngImpl implements RisenPartyintroducerMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenPartyintroducer findById(Integer id) {
		RisenPartyintroducer entity = dao.findById(id);
		return entity;
	}

	public RisenPartyintroducer save(RisenPartyintroducer bean) {
		dao.save(bean);
		return bean;
	}

	public RisenPartyintroducer update(RisenPartyintroducer bean) {
		Updater<RisenPartyintroducer> updater = new Updater<RisenPartyintroducer>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenPartyintroducer deleteById(Integer id) {
		RisenPartyintroducer bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenPartyintroducer[] deleteByIds(Integer[] ids) {
		RisenPartyintroducer[] beans = new RisenPartyintroducer[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenPartyintroducerDao dao;

	@Autowired
	public void setDao(RisenPartyintroducerDao dao) {
		this.dao = dao;
	}
}