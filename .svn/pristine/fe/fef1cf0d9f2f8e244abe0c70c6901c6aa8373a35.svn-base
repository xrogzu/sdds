package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteQuesDao;
import com.risen.entity.RisenVoteQues;
import com.risen.service.IRisenVoteQuesService;
@Service
@Transactional
public class RisenVoteQuesService implements IRisenVoteQuesService{
	
	public Pagination getPage(Integer pageNo, Integer pageSize,Integer voteId){
		return quesDao.getPage(pageNo, pageSize, voteId);
	}
	public RisenVoteQues save(RisenVoteQues voteQues){
		return quesDao.save(voteQues);
	}
	
	public void delete(Integer id){
		quesDao.delete(id);
	}
	
	public RisenVoteQues findById(Integer id){
		return quesDao.findById(id);
	}
	
	public RisenVoteQues update(RisenVoteQues voteQues){
		return quesDao.update(voteQues);
	}
	
	public List<RisenVoteQues> findListByVote(Integer voteId,Integer quesNature){
		return quesDao.findListByVote(voteId, quesNature);
	}
	
	public void sort(Integer id,Integer sort){
		quesDao.sort(id,sort);
	}
	
	@Autowired
	private IRisenVoteQuesDao quesDao;
}
