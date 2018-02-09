package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenJoinorgDao;
import com.risen.entity.RisenJoinorg;
import com.risen.service.RisenJoinorgMng;

@Service
@Transactional
public class RisenJoinorgMngImpl implements RisenJoinorgMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenJoinorg findById(Integer id) {
		RisenJoinorg entity = dao.findById(id);
		return entity;
	}

	public RisenJoinorg save(RisenJoinorg bean) {
		dao.save(bean);
		return bean;
	}

	public RisenJoinorg update(RisenJoinorg bean) {
		Updater<RisenJoinorg> updater = new Updater<RisenJoinorg>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenJoinorg deleteById(Integer id) {
		RisenJoinorg bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenJoinorg[] deleteByIds(Integer[] ids) {
		RisenJoinorg[] beans = new RisenJoinorg[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenJoinorgDao dao;

	@Autowired
	public void setDao(RisenJoinorgDao dao) {
		this.dao = dao;
	}
}