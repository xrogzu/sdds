package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.risen.dao.IRisenIntegralDao;
import com.risen.entity.RisenIntegral;
import com.risen.service.IRisenIntegralService;

@Service
@Transactional
public class RisenIntegralServiceImpl implements IRisenIntegralService {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public RisenIntegral findById(Integer id) {
		RisenIntegral entity = dao.findById(id);
		return entity;
	}

	public RisenIntegral save(RisenIntegral bean) {
		dao.save(bean);
		return bean;
	}
	
	public void deleteByIds(Integer[] ids) {
		
		for (int i = 0,len = ids.length; i < len; i++) {
			 delete(ids[i]);
		}
		
	}
	@Autowired
	private IRisenIntegralDao dao;

	public RisenIntegral update(RisenIntegral bean) {
		bean=dao.update(bean);
		return bean;
	}

	public void delete(Integer id) {
		dao.delete(id);
		
	}

	@Override
	public RisenIntegral findByOrgId(Integer id) {
		
		return dao.findByOrgId(id);
	}

	@Override
	public List<RisenIntegral> getReportData(Integer orgId) {
		 List<RisenIntegral>list=dao.getReportData(orgId);
		return list;
	}

	@Override
	public void updateBaseScore(Integer orgId, Integer score) {
		dao.updateBaseScore(orgId, score);
		
	}

	@Override
	public void batchUpdBaseScore(Integer[] orgId, Integer score) {
		dao.batchUpdBaseScore(orgId, score);
		
	}

	@Override
	public List<RisenIntegral> getPage(String departId) {
		// TODO Auto-generated method stub
		return dao.getPage(departId);
	}

	@Override
	public boolean existRecord(String year, String deptId) {
		// TODO Auto-generated method stub
		List<RisenIntegral> records = dao.getYearInfo(year, deptId);
		if(records==null || records.size()==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public List<RisenIntegral> getYearInfo(String year, String deptId) {
		// TODO Auto-generated method stub
		return dao.getYearInfo(year,deptId);
	}

	@Override
	public int changeRuleById(String id, String desc) {
		// TODO Auto-generated method stub
		return dao.changeRuleById(id,desc);
	}

	@Override
	public int changeBaseById(Integer id, Integer base) {
		// TODO Auto-generated method stub
		return dao.changeBaseById(id,base);
	}

	@Override
	public int batchUpBaseScore(Integer[] ids, Integer score) {
		// TODO Auto-generated method stub
		return dao.batchUpBaseScore(ids,score);
	}

	@Override
	public List<Object[]> getListse(String strIds, String channelName,String startDate, String endDate) {
		// TODO Auto-generated method stub
		return dao.getListse(strIds,channelName,startDate, endDate);
	}
}