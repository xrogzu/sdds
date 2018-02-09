package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteQuesContentDao;
import com.risen.entity.RisenVoteQuesContent;
import com.risen.service.IRisenVoteQuesContentService;
@Service
@Transactional
public class RisenVoteQuesContentService implements IRisenVoteQuesContentService{
	public Pagination getPage(Integer pageNo, Integer pageSize,Integer voteId){
		return quescontentDao.getPage(pageNo, pageSize,voteId);
	}
	public RisenVoteQuesContent save(RisenVoteQuesContent quesContent){
		return quescontentDao.save(quesContent);
	}
	
	public void delete(Integer id){
		quescontentDao.delete(id);
	}
	
	public RisenVoteQuesContent findById(Integer id){
		return quescontentDao.findById(id);
	}
	
	public RisenVoteQuesContent update(RisenVoteQuesContent quesContent){
		return quescontentDao.update(quesContent);
	}
	
	public List<RisenVoteQuesContent> findListByQues(Integer quesId){
		return quescontentDao.findListByQues(quesId);
	}
	@Autowired
	private IRisenVoteQuesContentDao quescontentDao;
}
