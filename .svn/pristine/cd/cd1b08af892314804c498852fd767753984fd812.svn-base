package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenRejectDao;
import com.risen.entity.RisenReject;
import com.risen.service.IRisenRejectService;

@Service
@Transactional
public class RisenRejectServiceImpl implements IRisenRejectService {

	@Override
	public Pagination getPage(Integer departId, int pageNo, int pageSize,int status) {
		// TODO Auto-generated method stub
		return dao.getPage(departId, pageNo, pageSize,status);
	}

	@Override
	public RisenReject save(RisenReject reject) {
		// TODO Auto-generated method stub
		return dao.save(reject);
	}

	@Override
	public RisenReject findById(Integer rejectId) {
		// TODO Auto-generated method stub
		return dao.findById(rejectId);
	}

	@Override
	public Integer changeStatus(Integer departId, Integer contentId) {
		// TODO Auto-generated method stub
		return dao.changeStatus(departId, contentId);
	}
	
	@Override
	public List<RisenReject> getListByDepartIdAndContentId(Integer departId,
			Integer contentId) {
		// TODO Auto-generated method stub
		return dao.getListByDepartIdAndContentId(departId,contentId);
	}
	
	@Override
	public Integer changeReason(Integer risenrjUnid, String reason) {
		// TODO Auto-generated method stub
		return dao.changeReason(risenrjUnid,reason);
	}
	
	@Autowired
	private IRisenRejectDao dao;

}
