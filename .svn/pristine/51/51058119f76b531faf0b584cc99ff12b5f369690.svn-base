package com.risen.dao;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenVoteItem;

public interface IRisenVoteItemDao {
	public Pagination getPage(Integer voteId,Integer quesId,Integer pageNo,Integer pageSize);
	public RisenVoteItem save(RisenVoteItem item);
	public RisenVoteItem findById(Integer id);
	public RisenVoteItem update(RisenVoteItem item);
	public void delete(Integer id);
	public void sort(Integer id,Integer sort);
	public List<RisenVoteItem> findListByVote(Integer voteId);
	public List<RisenVoteItem> findListByQues(Integer quesId);
	public void deleteByQues(Integer quesId);
}
