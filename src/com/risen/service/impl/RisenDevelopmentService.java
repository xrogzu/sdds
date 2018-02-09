package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.risen.dao.IRisenDevelopmentDao;
import com.risen.entity.RisenDevelopment;
import com.risen.service.IRisenDevelopmentService;

@Service
@Transactional
public class RisenDevelopmentService implements IRisenDevelopmentService {
	
	@Autowired IRisenDevelopmentDao risenDevelopmentDao;

	/**
	 * 保存
	 */
	public RisenDevelopment save(RisenDevelopment baseModel) {
		// TODO Auto-generated method stub
		return risenDevelopmentDao.save(baseModel);
	}
	/**
	 * 流程展示列表
	 */
	public List<RisenDevelopment> risenDevelopmentList(
			RisenDevelopment baseModel) {
		// TODO Auto-generated method stub
		return risenDevelopmentDao.risenDevelopmentList(baseModel);
	}
	/**
	 * 删除
	 */
	public RisenDevelopment deleteData(String uuid){
		return risenDevelopmentDao.deleteData(uuid);
	}
	
	/**
	 * 查询单个对象
	 */
	public RisenDevelopment getModel(String uuid){
		return risenDevelopmentDao.getModel(uuid);
	}
}
