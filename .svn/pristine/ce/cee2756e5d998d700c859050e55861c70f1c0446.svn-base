package com.risen.service;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenVoteQues;

public interface IRisenVoteQuesService {
	public Pagination getPage(Integer pageNo, Integer pageSize,Integer voteId);
	public RisenVoteQues save(RisenVoteQues voteQues);
	public void delete(Integer id);
	public RisenVoteQues findById(Integer id);
	public RisenVoteQues update(RisenVoteQues voteQues);
	public List<RisenVoteQues> findListByVote(Integer voteId,Integer quesNature);
	public void sort(Integer id,Integer sort);
}
