package com.risen.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.hibernate4.Updater.UpdateMode;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsDepartmentDao;
import com.jeecms.core.entity.CmsDepartment;
import com.risen.dao.RisenDevpartyDao;
import com.risen.entity.RisenDevparty;
import com.risen.entity.RisenPartyChange;
import com.risen.service.RisenDevpartyMng;

@Service
@Transactional
public class RisenDevpartyMngImpl implements RisenDevpartyMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize,String departId ) {
		Pagination page = dao.getPage(pageNo, pageSize, departId);
		return page;
	}

	@Transactional(readOnly = true)
	public RisenDevparty findById(Integer id) {
		RisenDevparty entity = dao.findById(id);
		return entity;
	}

	public RisenDevparty save(RisenDevparty bean) {
		dao.save(bean);
		return bean;
	}

	public RisenDevparty update(RisenDevparty bean) {
		Updater<RisenDevparty> updater = new Updater<RisenDevparty>(bean,UpdateMode.MIDDLE);
		RisenDevparty entity = dao.updateByUpdater(updater);
		return entity;
	}

	public RisenDevparty deleteById(Integer id) {
		RisenDevparty bean = dao.deleteById(id);
		return bean;
	}
	
	public RisenDevparty[] deleteByIds(Integer[] ids) {
		RisenDevparty[] beans = new RisenDevparty[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private RisenDevpartyDao dao;
	
	@Autowired
	private CmsDepartmentDao departDao;
	@Autowired
	public void setDao(RisenDevpartyDao dao) {
		this.dao = dao;
	}
	@Override
	public RisenDevparty updateWithRisenPartyChange(RisenPartyChange bean) {
		// TODO Auto-generated method stub
		return dao.updateWithRisenPartyChange(bean);
	}
	@Override
	public Pagination getAllActives(Integer departId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String departIds = "";
		if(departId!=null){
			if(departId.equals(1)){
				departIds = "1";
			}else{
				List<CmsDepartment> depts = departDao.getAllTypeDeptById(departId,null);
				if(depts!=null && depts.size()>0){
					for (CmsDepartment depart : depts) {
						departIds = departIds + "'" + depart.getId() + "',";
					}
					departIds = StringUtils.removeEnd(departIds,",");
				}
			}
		}
		return dao.getAllActives(departIds,pageNo,pageSize);
	}
}