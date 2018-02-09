package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenDiscussionDao;
import com.risen.entity.RisenDiscussion;
import com.risen.service.IRisenDiscussionService;

@Service
@Transactional
public class RisenDiscussionServiceImpl implements IRisenDiscussionService {

	@Override
	public RisenDiscussion save(RisenDiscussion bean) {
		// TODO Auto-generated method stub
		return dao.save(bean);
	}

	@Override
	public RisenDiscussion update(RisenDiscussion bean) {
		// TODO Auto-generated method stub
		return dao.update(bean);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public boolean exist(Integer userId, Integer departId, Integer year) {
		// TODO Auto-generated method stub
		Pagination pagination = dao.getPage(userId, departId, year);
		if(pagination==null){
			return true;
		}else{
			List<RisenDiscussion> discussions = (List<RisenDiscussion>) pagination.getList();
			if(discussions.size()>0){
				return true;
			}else{
				return false;
			}
		}
	}
	
	@Override
	public Pagination getMyDiscussion(int pageNo, int pageSize,Integer userId, Integer departId) {
		// TODO Auto-generated method stub
		return dao.getPage(pageNo, pageSize, userId, departId);
	}
	
	@Override
	public RisenDiscussion findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
	
	@Autowired
	private IRisenDiscussionDao dao;
	
}
