package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenCommitteeexaminationDao;
import com.risen.entity.RisenCommitteeexamination;
import com.risen.service.RisenCommitteeexaminationMng;

@Service
@Transactional
public class RisenCommitteeexaminationMngImpl implements RisenCommitteeexaminationMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenCommitteeexamination findById(Integer id) {
		RisenCommitteeexamination entity = dao.findById(id);
		return entity;
	}

	public RisenCommitteeexamination save(RisenCommitteeexamination bean) {
		dao.save(bean);
		return bean;
	}

	public RisenCommitteeexamination update(RisenCommitteeexamination bean) {
		Updater<RisenCommitteeexamination> updater = new Updater<RisenCommitteeexamination>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public RisenCommitteeexamination deleteById(Integer id) {
		RisenCommitteeexamination bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenCommitteeexamination[] deleteByIds(Integer[] ids) {
		RisenCommitteeexamination[] beans = new RisenCommitteeexamination[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenCommitteeexaminationDao dao;

	@Autowired
	public void setDao(RisenCommitteeexaminationDao dao) {
		this.dao = dao;
	}
}