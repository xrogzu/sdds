package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenVoteItemDao;
import com.risen.entity.RisenVoteItem;
import com.risen.service.IRisenVoteItemService;
@Service
@Transactional
public class RisenVoteItemService implements IRisenVoteItemService{
	public Pagination getPage(Integer voteId,Integer quesId,Integer pageNo, Integer pageSize){
		return itemDao.getPage(voteId,quesId,pageNo,pageSize);
	}
	public RisenVoteItem save(RisenVoteItem item){
		return itemDao.save(item);
	}
	public RisenVoteItem findById(Integer id){
		return itemDao.findById(id);
	}
	public RisenVoteItem update(RisenVoteItem item){
		return itemDao.update(item);
	}
	public void delete(Integer id){
		 itemDao.delete(id);
	}
	public void sort(Integer id,Integer sort){
		itemDao.sort(id, sort);
	}
	public List<RisenVoteItem> findListByVote(Integer voteId){
		return itemDao.findListByVote(voteId);
	}
	public List<RisenVoteItem> findListByQues(Integer quesId){
		return itemDao.findListByQues(quesId);
	}
	public void deleteByQues(Integer quesId){
		itemDao.deleteByQues(quesId);
	}
	@Autowired
	private IRisenVoteItemDao itemDao;
}
