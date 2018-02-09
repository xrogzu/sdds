package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenPublicnotifyDao;
import com.risen.entity.RisenPublicnotify;
import com.risen.service.IRisenPublicnotifyService;

@Service
@Transactional
public class RisenPublicnotifyServiceImpl implements IRisenPublicnotifyService {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenPublicnotify findById(Integer id) {
		RisenPublicnotify entity = dao.findById(id);
		return entity;
	}

	public RisenPublicnotify save(RisenPublicnotify bean) {
		dao.save(bean);
		return bean;
	}

	

	public RisenPublicnotify deleteById(Integer id) {
		RisenPublicnotify bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenPublicnotify[] deleteByIds(Integer[] ids) {
		RisenPublicnotify[] beans = new RisenPublicnotify[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private IRisenPublicnotifyDao dao;

	@Autowired
	public void setDao(IRisenPublicnotifyDao dao) {
		this.dao = dao;
	}

	public RisenPublicnotify update(RisenPublicnotify bean) {
		
		return dao.update(bean);
	}
}