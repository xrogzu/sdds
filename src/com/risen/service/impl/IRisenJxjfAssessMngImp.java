package com.risen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.risen.dao.IRisenJxjfAssessDao;
import com.risen.entity.RisenJxjfAssess;
import com.risen.service.RisenJxjfAssessMng;
@Service
@Transactional
public class IRisenJxjfAssessMngImp implements RisenJxjfAssessMng{

	@Override
	public List<RisenJxjfAssess> findbydepartpg(Integer departid) {
		return dao.findbydepartpg(departid);
	}
	@Autowired
	private IRisenJxjfAssessDao dao;
	public IRisenJxjfAssessDao getDao() {
		return dao;
	}
	public void setDao(IRisenJxjfAssessDao dao) {
		this.dao = dao;
	}
	@Override
	public String save(RisenJxjfAssess jxpg) {
		// TODO Auto-generated method stub
		return dao.save(jxpg);
	}
	@Override
	public RisenJxjfAssess findbyid(Integer id) {
		// TODO Auto-generated method stub
		return dao.findbyid(id);
	}
	@Override
	public String update(RisenJxjfAssess jxpg) {
		// TODO Auto-generated method stub
		return dao.update(jxpg);
	}
	public String findbychannelname(Integer departid,String channelname){
		return dao.findbychannelname(departid,channelname);
	}
}
