package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenCentralizedtrainDao;
import com.risen.entity.RisenCentralizedtrain;
import com.risen.service.RisenCentralizedtrainMng;

@Service
@Transactional
public class RisenCentralizedtrainMngImpl implements RisenCentralizedtrainMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenCentralizedtrain findById(Integer id) {
		RisenCentralizedtrain entity = dao.findById(id);
		return entity;
	}

	public RisenCentralizedtrain save(RisenCentralizedtrain bean) {
		dao.save(bean);
		return bean;
	}

	public RisenCentralizedtrain update(RisenCentralizedtrain bean) {
		Updater<RisenCentralizedtrain> updater = new Updater<RisenCentralizedtrain>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenCentralizedtrain deleteById(Integer id) {
		RisenCentralizedtrain bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenCentralizedtrain[] deleteByIds(Integer[] ids) {
		RisenCentralizedtrain[] beans = new RisenCentralizedtrain[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenCentralizedtrainDao dao;

	@Autowired
	public void setDao(RisenCentralizedtrainDao dao) {
		this.dao = dao;
	}
}