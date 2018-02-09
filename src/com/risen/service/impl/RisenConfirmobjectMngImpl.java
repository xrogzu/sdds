package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenConfirmobjectDao;
import com.risen.entity.RisenConfirmobject;
import com.risen.service.RisenConfirmobjectMng;

@Service
@Transactional
public class RisenConfirmobjectMngImpl implements RisenConfirmobjectMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenConfirmobject findById(Integer id) {
		RisenConfirmobject entity = dao.findById(id);
		return entity;
	}

	public RisenConfirmobject save(RisenConfirmobject bean) {
		dao.save(bean);
		return bean;
	}

	public RisenConfirmobject update(RisenConfirmobject bean) {
		Updater<RisenConfirmobject> updater = new Updater<RisenConfirmobject>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenConfirmobject deleteById(Integer id) {
		RisenConfirmobject bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenConfirmobject[] deleteByIds(Integer[] ids) {
		RisenConfirmobject[] beans = new RisenConfirmobject[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenConfirmobjectDao dao;

	@Autowired
	public void setDao(RisenConfirmobjectDao dao) {
		this.dao = dao;
	}
}