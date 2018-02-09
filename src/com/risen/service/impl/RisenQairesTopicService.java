package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenQairesTopicDao;
import com.risen.entity.RisenQairesTopic;
import com.risen.service.IRisenQairesTopicService;
@Service
@Transactional
public class RisenQairesTopicService implements IRisenQairesTopicService{
	public Pagination getPage(Integer qairesId,Integer pageNo, Integer pageSize){
		return topicDao.getPage(qairesId,pageNo,pageSize);
	}
	
	public RisenQairesTopic save(RisenQairesTopic qairesTopic){
		return topicDao.save(qairesTopic);
	}
	
	public void delete(Integer id){
		topicDao.delete(id);
	}
	
	public RisenQairesTopic findById(Integer id){
		return topicDao.findById(id);
	}
	
	public List<RisenQairesTopic> finList(Integer qairesId){
		return topicDao.finList( qairesId);
	}
	
	public void sort(Integer id,Integer sort){
		topicDao.sort(id,sort);
	}
	
	public RisenQairesTopic update(RisenQairesTopic qairesTopic){
		return topicDao.update(qairesTopic);
	}
	@Autowired
	private IRisenQairesTopicDao topicDao;
}
