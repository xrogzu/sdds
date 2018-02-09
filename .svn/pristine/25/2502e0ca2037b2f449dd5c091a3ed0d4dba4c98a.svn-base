package com.risen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenQairesDetailDao;
import com.risen.entity.RisenQairesDetail;
import com.risen.service.IRisenQairesDetailService;
@Service
@Transactional
public class RisenQairesDetailService implements IRisenQairesDetailService{
	
	public RisenQairesDetail save(RisenQairesDetail detail){
		return qairesdetailDao.save(detail);
	}
	
	public Pagination getPage(Integer recordId,Integer pageNo,Integer pageSize){
		return qairesdetailDao.getPage(recordId, pageNo, pageSize);
	}
	
	public void deleteByTopic(Integer topicId){
		qairesdetailDao.deleteByTopic(topicId);
	}
	
	public RisenQairesDetail findById(Integer id){
		return qairesdetailDao.findById(id);
	}
	
	public void deleteByRecord(Integer recordId){
		qairesdetailDao.deleteByRecord(recordId);
	}
	@Autowired
	private IRisenQairesDetailDao qairesdetailDao;
}
