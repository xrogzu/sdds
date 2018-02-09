package com.risen.service;

public interface IRisenCheckIdentityIdService {
	
	// 查询数据库里是否有该身份证号
	public boolean checkIsExist(String identityCardId);

}
