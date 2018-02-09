package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenFostereducationDao;
import com.risen.entity.RisenFostereducation;
import com.risen.service.RisenFostereducationMng;

@Service
@Transactional
public class RisenFostereducationMngImpl implements RisenFostereducationMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenFostereducation findById(Integer id) {
		RisenFostereducation entity = dao.findById(id);
		return entity;
	}

	public RisenFostereducation save(RisenFostereducation bean) {
		dao.save(bean);
		return bean;
	}

	public RisenFostereducation update(RisenFostereducation bean) {
		Updater<RisenFostereducation> updater = new Updater<RisenFostereducation>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenFostereducation deleteById(Integer id) {
		RisenFostereducation bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenFostereducation[] deleteByIds(Integer[] ids) {
		RisenFostereducation[] beans = new RisenFostereducation[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenFostereducationDao dao;

	@Autowired
	public void setDao(RisenFostereducationDao dao) {
		this.dao = dao;
	}
}