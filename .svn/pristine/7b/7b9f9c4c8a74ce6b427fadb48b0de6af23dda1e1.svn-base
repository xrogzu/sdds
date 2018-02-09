package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.risen.dao.IRisenEnsureActiveDao;
import com.risen.entity.RisenActive;
import com.risen.service.IRisenEnsureActiveService;

public class RisenEnsureActiveService implements IRisenEnsureActiveService {

	@Autowired
	IRisenEnsureActiveDao risenEnsureActiveDao;
	
	public RisenActive toSave(RisenActive bean) {
		return risenEnsureActiveDao.save(bean);
		
	}

}
