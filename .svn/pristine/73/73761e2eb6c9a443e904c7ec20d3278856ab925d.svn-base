package com.risen.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenTargetDao;
import com.risen.dao.IRisenTargetDetailDao;
import com.risen.entity.RisenTargetDetail;
import com.risen.service.IRisenTargetDetailService;
@Service
@Transactional
public class RisenTargetDetailService implements IRisenTargetDetailService{
	@Autowired
	private IRisenTargetDetailDao risenTargetDetailDao;
	@Override
	public void batchUpdBaseScore(Integer[] orgId, Integer score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RisenTargetDetail findById(Integer id) {
		// TODO Auto-generated method stub
		return risenTargetDetailDao.findById(id);
	}

	@Override
	public Pagination findByOrgId(int pageNo, int pageSize,Integer id) {
		// TODO Auto-generated method stub
		return risenTargetDetailDao.findByOrgId(pageNo, pageSize,id);
	}

	@Override
	public Pagination getPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return risenTargetDetailDao.getPage(pageNo, pageSize);
	}

	@Override
	public List<RisenTargetDetail> getReportData(Integer orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RisenTargetDetail save(RisenTargetDetail voteQues) {
		// TODO Auto-generated method stub
		return risenTargetDetailDao.save(voteQues);
	}

	@Override
	public RisenTargetDetail update(RisenTargetDetail bean) {
		// TODO Auto-generated method stub
		return risenTargetDetailDao.update(bean);
	}

	@Override
	public void updateBaseScore(Integer orgId, Integer score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pagination findByParendId(int pageNo, int pageSize, Integer id) {
		// TODO Auto-generated method stub
		return risenTargetDetailDao.findByParentId(pageNo, pageSize,id);
	}

	@Override
	public RisenTargetDetail showInfo(Integer parentid, Integer deptId) {
		// TODO Auto-generated method stub
		return risenTargetDetailDao.findByParentId(parentid, deptId);
	}

	@Override
	public RisenTargetDetail updateBase(RisenTargetDetail bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination getAllSub(int pageNo, int pageSize, String parentId) {
		// TODO Auto-generated method stub
		return risenTargetDetailDao.showAllSub(pageNo,pageSize,parentId);
	}

	@Override
	public Pagination getMyTarget(int pageNo, int pageSize, String deptId) {
		// TODO Auto-generated method stub
		return risenTargetDetailDao.getMyTarget(pageNo,pageSize,deptId);
	}
	@Override
	public List<RisenTargetDetail> getAllFinishedList(Integer id) {
		// TODO Auto-generated method stub
		return risenTargetDetailDao.getAllFinishedList(id, "9");
	}

	@Override
	public List<RisenTargetDetail> getAllUnfinishedList(Integer departId,
			Integer id) {
		// TODO Auto-generated method stub
		return risenTargetDetailDao.getAllUnfinishedList(departId, id);
	}

	@Override
	public void deleteByTargetId(Integer targetId) {
		// TODO Auto-generated method stub
		risenTargetDetailDao.deleteByPid(targetId);
	}
	
}
