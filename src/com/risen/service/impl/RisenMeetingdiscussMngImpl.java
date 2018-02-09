package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenMeetingdiscussDao;
import com.risen.entity.RisenMeetingdiscuss;
import com.risen.service.RisenMeetingdiscussMng;

@Service
@Transactional
public class RisenMeetingdiscussMngImpl implements RisenMeetingdiscussMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenMeetingdiscuss findById(Integer id) {
		RisenMeetingdiscuss entity = dao.findById(id);
		return entity;
	}

	public RisenMeetingdiscuss save(RisenMeetingdiscuss bean) {
		dao.save(bean);
		return bean;
	}

	public RisenMeetingdiscuss update(RisenMeetingdiscuss bean) {
		Updater<RisenMeetingdiscuss> updater = new Updater<RisenMeetingdiscuss>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenMeetingdiscuss deleteById(Integer id) {
		RisenMeetingdiscuss bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenMeetingdiscuss[] deleteByIds(Integer[] ids) {
		RisenMeetingdiscuss[] beans = new RisenMeetingdiscuss[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenMeetingdiscussDao dao;

	@Autowired
	public void setDao(RisenMeetingdiscussDao dao) {
		this.dao = dao;
	}
}