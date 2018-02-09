package com.risen.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenTargetDao;
import com.risen.dao.IRisenTargetDetailDao;
import com.risen.entity.RisenTarget;
import com.risen.entity.RisenTargetDetail;
import com.risen.service.IRisenTargetService;
@Service
@Transactional
public class RisenTargetService implements IRisenTargetService{
	@Autowired
	private IRisenTargetDao risenTargetDao;
	@Autowired
	private IRisenTargetDetailDao detailDao;

	@Override
	public void batchUpdBaseScore(Integer[] orgId, Integer score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		risenTargetDao.delete(id);
	}

	@Override
	public RisenTarget findById(Integer id) {
		// TODO Auto-generated method stub
		return risenTargetDao.findById(id);
	}

	@Override
	public Pagination findByOrgId(int pageNo, int pageSize,Integer id) {
		// TODO Auto-generated method stub
		return risenTargetDao.findByOrgId(pageNo, pageSize, id);
	}

	@Override
	public Pagination getPage(int pageNo, int pageSize,String id,Integer deptId) {
		// TODO Auto-generated method stub
		return risenTargetDao.getPage(pageNo, pageSize,id,deptId);
	}
	/***
	 * 统计每个党组织的总分
	 */
	public List<RisenTarget> getReportList(String orgIds){
		return risenTargetDao.getReportList(orgIds);
	}
	@Override
	public List<RisenTarget> getReportData(Integer orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RisenTarget save(RisenTarget voteQues) {
		// TODO Auto-generated method stub
		
		return risenTargetDao.save(voteQues);
	}

	@Override
	public RisenTarget update(RisenTarget bean) {
		// TODO Auto-generated method stub
		return risenTargetDao.update(bean);
	}

	@Override
	public void updateBaseScore(Integer orgId, Integer score) {
		// TODO Auto-generated method stub
		
	}
	
	
}
