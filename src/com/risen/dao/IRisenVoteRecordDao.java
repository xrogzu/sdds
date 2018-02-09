package com.risen.dao;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenVoteRecord;

public interface IRisenVoteRecordDao {
	public Pagination getPage(Integer voteId,Integer pageNo, Integer pageSize);
	public RisenVoteRecord save(RisenVoteRecord voteRecord);
	public void delete(Integer id);
	public RisenVoteRecord findById(Integer id) ;
	public RisenVoteRecord update(RisenVoteRecord voteRecord);
	//
	public void deleteByVote(Integer voteId);
	public List<RisenVoteRecord> findListByVote(Integer voteId,String recordPhone);
}
