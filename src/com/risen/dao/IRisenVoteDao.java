package com.risen.dao;



import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenVote;

public interface IRisenVoteDao {
	public Pagination getPage(String startTime,String endTime,Integer pageNo, Integer pageSize,
			Boolean status);
	public RisenVote save(RisenVote bean);
	public RisenVote findById(Integer id);
	public RisenVote update(RisenVote bean);
	public void delete(Integer id);
}
