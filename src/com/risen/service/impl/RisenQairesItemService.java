package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenQairesItemDao;
import com.risen.entity.RisenQairesItem;
import com.risen.service.IRisenQairesItemService;
@Service
@Transactional
public class RisenQairesItemService implements IRisenQairesItemService{
	public List<RisenQairesItem> findList(Integer topicId){
		return qairesItemDao.findList(topicId);
	}
	
	public Pagination getPage(Integer topicId,Integer pageNo, Integer pageSize){
		return qairesItemDao.getPage(topicId, pageNo,  pageSize);
	}
	
	public RisenQairesItem save(RisenQairesItem qairesItem){
		return qairesItemDao.save(qairesItem);
	}
	
	public void delete(Integer id){
		qairesItemDao.delete(id);
	}
	
	public RisenQairesItem findById(Integer id){
		return qairesItemDao.findById(id);
	}
	public RisenQairesItem updater(RisenQairesItem qairesItem){
		Updater<RisenQairesItem> updater;
		updater = new Updater<RisenQairesItem>(qairesItem);
		return qairesItemDao.updateByUpdater(updater);
	}
	public RisenQairesItem update(RisenQairesItem qairesItem){
		return qairesItemDao.update(qairesItem);
	}
		
	public void deleteByTopic(Integer topicId){
		qairesItemDao.deleteByTopic( topicId);
	}
	
@Autowired
private IRisenQairesItemDao qairesItemDao;
}
