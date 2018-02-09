package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenMaterialfilingDao;
import com.risen.entity.RisenMaterialfiling;
import com.risen.service.RisenMaterialfilingMng;

@Service
@Transactional
public class RisenMaterialfilingMngImpl implements RisenMaterialfilingMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenMaterialfiling findById(Integer id) {
		RisenMaterialfiling entity = dao.findById(id);
		return entity;
	}

	public RisenMaterialfiling save(RisenMaterialfiling bean) {
		dao.save(bean);
		return bean;
	}

	public RisenMaterialfiling update(RisenMaterialfiling bean) {
		Updater<RisenMaterialfiling> updater = new Updater<RisenMaterialfiling>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenMaterialfiling deleteById(Integer id) {
		RisenMaterialfiling bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenMaterialfiling[] deleteByIds(Integer[] ids) {
		RisenMaterialfiling[] beans = new RisenMaterialfiling[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenMaterialfilingDao dao;

	@Autowired
	public void setDao(RisenMaterialfilingDao dao) {
		this.dao = dao;
	}
}