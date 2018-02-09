package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenCommitteereviewDao;
import com.risen.entity.RisenCommitteereview;
import com.risen.service.RisenCommitteereviewMng;

@Service
@Transactional
public class RisenCommitteereviewMngImpl implements RisenCommitteereviewMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenCommitteereview findById(Integer id) {
		RisenCommitteereview entity = dao.findById(id);
		return entity;
	}

	public RisenCommitteereview save(RisenCommitteereview bean) {
		dao.save(bean);
		return bean;
	}

	public RisenCommitteereview update(RisenCommitteereview bean) {
		Updater<RisenCommitteereview> updater = new Updater<RisenCommitteereview>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenCommitteereview deleteById(Integer id) {
		RisenCommitteereview bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenCommitteereview[] deleteByIds(Integer[] ids) {
		RisenCommitteereview[] beans = new RisenCommitteereview[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenCommitteereviewDao dao;

	@Autowired
	public void setDao(RisenCommitteereviewDao dao) {
		this.dao = dao;
	}
}