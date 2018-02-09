package com.risen.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteDao;
import com.risen.entity.RisenVote;
import com.risen.service.IRisenVoteService;
@Service
@Transactional
public class RisenVoteService implements IRisenVoteService{
	//投票页面展示
	public Pagination getPage(String startTime,String endTime,Integer pageNo, Integer pageSize,
			Boolean status){
		return voteDao.getPage(startTime, endTime, pageNo, pageSize, status);
	}
	//投票页面添加保存
	public RisenVote save(RisenVote vote){
		return voteDao.save(vote);
	}
	public RisenVote findById(Integer id) {
		return voteDao.findById(id);
	}
	public RisenVote update(RisenVote vote){
		return voteDao.update(vote);
	}
	public void delete(Integer id){
			voteDao.delete(id);
	}
	
	@Autowired
	private IRisenVoteDao voteDao;
}
