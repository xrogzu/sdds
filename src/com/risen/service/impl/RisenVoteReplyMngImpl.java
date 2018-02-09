package com.risen.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.RisenVoteReplyDao;
import com.risen.entity.RisenVoteReply;
import com.risen.service.RisenVoteReplyMng;
@Service
@Transactional
public class RisenVoteReplyMngImpl implements RisenVoteReplyMng{

	private RisenVoteReplyDao dao;
	public RisenVoteReplyDao getDao() {
		return dao;
	}
	@Autowired
	public void setDao(RisenVoteReplyDao dao) {
		this.dao = dao;
	}

	@Override
	public RisenVoteReply save(RisenVoteReply risenVoteReply) {
		// TODO Auto-generated method stub
		return dao.save(risenVoteReply);
	}

	@Override
	public RisenVoteReply update(RisenVoteReply risenVoteReply) {
		// TODO Auto-generated method stub
		return dao.update(risenVoteReply);
	}

	@Override
	public void delete(RisenVoteReply risenVoteReply) {
		// TODO Auto-generated method stub
		dao.delete(risenVoteReply);
	}

	@Override
	public RisenVoteReply findById(RisenVoteReply risenVoteReply) {
		// TODO Auto-generated method stub
		return dao.findById(risenVoteReply);
	}

	@Override
	public Pagination findByVoteId(Integer voteId,int pageNo, int pageSize) {
		return dao.findByVoteId(voteId, pageNo, pageSize);
	}


}
