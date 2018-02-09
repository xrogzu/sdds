package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenTalkDao;
import com.risen.entity.RisenTalk;
import com.risen.service.RisenTalkMng;

@Service
@Transactional
public class RisenTalkMngImpl implements RisenTalkMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenTalk findById(Integer id) {
		RisenTalk entity = dao.findById(id);
		return entity;
	}

	public RisenTalk save(RisenTalk bean) {
		dao.save(bean);
		return bean;
	}

	public RisenTalk update(RisenTalk bean) {
		Updater<RisenTalk> updater = new Updater<RisenTalk>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenTalk deleteById(Integer id) {
		RisenTalk bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenTalk[] deleteByIds(Integer[] ids) {
		RisenTalk[] beans = new RisenTalk[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenTalkDao dao;

	@Autowired
	public void setDao(RisenTalkDao dao) {
		this.dao = dao;
	}
}