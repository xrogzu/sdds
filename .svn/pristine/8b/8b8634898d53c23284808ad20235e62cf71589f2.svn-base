package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteRecordDetailDao;
import com.risen.entity.RisenVoteRecordDetail;
import com.risen.service.IRisenVoteRecordDetailService;
@Service
@Transactional
public class RisenVoteRecordDetailService implements IRisenVoteRecordDetailService{
	public Pagination getPage(Integer recordId,Integer pageNo, Integer pageSize){
		return recordDetailDao.getPage(recordId,pageNo, pageSize);
	}
	
	public RisenVoteRecordDetail save(RisenVoteRecordDetail voteRecordDetail){
		return recordDetailDao.save(voteRecordDetail);
	}
	
	public void delete(Integer id){
		recordDetailDao.delete(id);
	}
	
	public RisenVoteRecordDetail findById(Integer id){
		return recordDetailDao.findById(id);
	}
	
	public RisenVoteRecordDetail update(RisenVoteRecordDetail voteRecordDetail){
		return recordDetailDao.update(voteRecordDetail);
	}
	
	public List<RisenVoteRecordDetail> findListByRecord(Integer recordId){
		return recordDetailDao.findListByRecord(recordId);
	}
	
	public void deleteByRecord(Integer recordId){
		recordDetailDao.deleteByRecord(recordId);
	}
@Autowired
private IRisenVoteRecordDetailDao recordDetailDao;
}
