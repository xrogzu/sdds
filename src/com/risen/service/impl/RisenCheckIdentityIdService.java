package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.risen.dao.IRisenCheckIdentityIdDao;
import com.risen.service.IRisenCheckIdentityIdService;

public class RisenCheckIdentityIdService implements IRisenCheckIdentityIdService {

	@Autowired
	IRisenCheckIdentityIdDao risenCheckIdentityIdDao;
	public boolean checkIsExist(String identityCardId) {
		return risenCheckIdentityIdDao.checkIsExist(identityCardId);
		
	}

}
