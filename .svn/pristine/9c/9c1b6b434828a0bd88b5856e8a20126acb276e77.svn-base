package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.risen.dao.IRisenQairesRecordDao;
import com.risen.entity.RisenQairesRecord;
import com.risen.service.IRisenQairesRecordService;
@Service
@Transactional
public class RisenQairesRecordService implements IRisenQairesRecordService{

	public RisenQairesRecord save(RisenQairesRecord record){
		return qairesrecordDao.save(record);
	}
	
	public void delete(Integer id){
		qairesrecordDao.delete(id);
	}
	
	public RisenQairesRecord findById(Integer id){
		return qairesrecordDao.findById(id);
	}
	
	public RisenQairesRecord update(RisenQairesRecord record){
		return qairesrecordDao.update(record);
	}
	
	public Pagination getPage(Integer qairesId,String userName,String startDate,String endDate,Integer pageNo,Integer pageSize){
		return qairesrecordDao.getPage(qairesId,userName, startDate, endDate, pageNo, pageSize);
	}
	
	public List<RisenQairesRecord> getList(Integer qairesId,String userName,String startDate,String endDate){
		return qairesrecordDao.getList(qairesId,userName, startDate, endDate);
	}
	public List<RisenQairesRecord> findList(Integer qairesId,CmsUser user){
		return qairesrecordDao.findList(qairesId,user);
	}
	
	public void deleteByQaires(Integer qaireId){
		qairesrecordDao.deleteByQaires(qaireId);
	}
	
	public void deleteByUser(Integer userId){
		qairesrecordDao.deleteByUser(userId);
	}
	@Autowired
	private IRisenQairesRecordDao qairesrecordDao;
}
