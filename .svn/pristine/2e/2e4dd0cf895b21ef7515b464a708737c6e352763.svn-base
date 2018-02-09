package com.risen.service;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenVoteRecord;

public interface IRisenVoteRecordService {
	public Pagination getPage(Integer voteId,Integer pageNo, Integer pageSize);
	public RisenVoteRecord save(RisenVoteRecord rsVoteRecord);
	public void delete(Integer id);
	public RisenVoteRecord findById(Integer id) ;
	public RisenVoteRecord update(RisenVoteRecord rsVoteRecord);
	//
	public List<RisenVoteRecord> findListByVote(Integer voteId,String recordPhone);
	public void deleteByVote(Integer voteId);
}
