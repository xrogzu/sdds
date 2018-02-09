package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteRecordDao;
import com.risen.entity.RisenVoteRecord;
import com.risen.service.IRisenVoteRecordService;
@Service
@Transactional
public class RisenVoteRecordService implements IRisenVoteRecordService{
	public Pagination getPage(Integer voteId,Integer pageNo, Integer pageSize){
		return recordDao.getPage(voteId,pageNo, pageSize);
	}
	
	public RisenVoteRecord save(RisenVoteRecord voteRecord){
		return recordDao.save(voteRecord);
	}
	
	public void delete(Integer id){
		recordDao.delete(id);
	}
	
	public RisenVoteRecord findById(Integer id){
		return recordDao.findById(id);
	}
	
	public RisenVoteRecord update(RisenVoteRecord voteRecord){
		return recordDao.update(voteRecord);
	}
	
	public List<RisenVoteRecord> findListByVote(Integer voteId,String recordPhone){
		return recordDao.findListByVote(voteId, recordPhone);
	}
	
	public void deleteByVote(Integer voteId){
		recordDao.deleteByVote(voteId);
	}
@Autowired
private IRisenVoteRecordDao recordDao;
}
