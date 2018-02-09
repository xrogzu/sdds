package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenQairesDao;
import com.risen.entity.RisenQaires;
import com.risen.service.IRisenQairesService;
@Service
@Transactional
public class RisenQairesService implements IRisenQairesService{
	public Pagination getPage(String startTime,String endTime,Integer pageNo, Integer pageSize,Boolean disable,Integer status){
		return qairesDao.getPage(startTime,endTime,pageNo, pageSize,disable,status);
	}
	public RisenQaires save(RisenQaires qaires){
		return qairesDao.save(qaires);
	}
	
	public void delete(Integer id){
		qairesDao.delete(id);
	}
	
	public RisenQaires findById(Integer id){
		return qairesDao.findById(id);
	}
	
	public RisenQaires update(RisenQaires qaires){
		return qairesDao.update(qaires);
	}
	public Pagination findList(Integer pageNo, Integer pageSize,Integer status) {
		return qairesDao.findList(pageNo,pageSize,status);
	}
@Autowired
private IRisenQairesDao qairesDao;
}
