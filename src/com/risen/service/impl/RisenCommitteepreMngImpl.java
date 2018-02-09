package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenCommitteepreDao;
import com.risen.entity.RisenCommitteepre;
import com.risen.service.RisenCommitteepreMng;

@Service
@Transactional
public class RisenCommitteepreMngImpl implements RisenCommitteepreMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenCommitteepre findById(Integer id) {
		RisenCommitteepre entity = dao.findById(id);
		return entity;
	}

	public RisenCommitteepre save(RisenCommitteepre bean) {
		dao.save(bean);
		return bean;
	}

	public RisenCommitteepre update(RisenCommitteepre bean) {
		Updater<RisenCommitteepre> updater = new Updater<RisenCommitteepre>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenCommitteepre deleteById(Integer id) {
		RisenCommitteepre bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenCommitteepre[] deleteByIds(Integer[] ids) {
		RisenCommitteepre[] beans = new RisenCommitteepre[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenCommitteepreDao dao;

	@Autowired
	public void setDao(RisenCommitteepreDao dao) {
		this.dao = dao;
	}
}