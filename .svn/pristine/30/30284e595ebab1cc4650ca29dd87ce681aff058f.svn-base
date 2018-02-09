package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenQairesContentDao;
import com.risen.entity.RisenQairesContent;
import com.risen.service.IRisenQairesContentService;
@Service
@Transactional
public class RisenQairesContentService implements IRisenQairesContentService{
	public RisenQairesContent save(RisenQairesContent content){
		return qairesContentDao.save(content);
	}
	
	public Pagination getPage(Integer topicId,Integer pageNo, Integer pageSize){
		return qairesContentDao.getPage( topicId, pageNo,  pageSize);
	}
	
	public void deleteByRecord(Integer recordId){
		qairesContentDao.deleteByRecord( recordId);
	}
	@Autowired
	IRisenQairesContentDao qairesContentDao;
}
