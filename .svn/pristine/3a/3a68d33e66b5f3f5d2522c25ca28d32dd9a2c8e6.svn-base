package com.risen.dao;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenVoteQues;

public interface IRisenVoteQuesDao {
	public Pagination getPage(Integer pageNo, Integer pageSize,Integer voteId);
	public RisenVoteQues save(RisenVoteQues voteQues);
	public RisenVoteQues findById(Integer id);
	public void delete(Integer id);
	public RisenVoteQues update(RisenVoteQues bean);
	public List<RisenVoteQues> findListByVote(Integer voteId,Integer quesNature);
	public void sort(Integer id,Integer sort);
}
