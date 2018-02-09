package com.risen.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.manager.CmsDepartmentMng;
import com.jeecms.core.manager.CmsUserMng;
import com.risen.dao.IRisenPartyChangeDao;
import com.risen.entity.RisenDevparty;
import com.risen.entity.RisenPartyChange;
import com.risen.service.IRisenPartyChangeService;
@Service
@Transactional
public class RisenPartyChangeServiceImpl implements IRisenPartyChangeService {

	@Override
	public RisenPartyChange save(RisenPartyChange bean) {
		// TODO Auto-generated method stub
		return dao.save(bean);
	}

	@Override
	public Pagination getAllInfoByDepartId(int pageNo, int pageSize,
			String risenpcDeptid, String changeType) {
		// TODO Auto-generated method stub
		return dao.getAllInfoByDepartId(pageNo, pageSize, risenpcDeptid, changeType);
	}

	@Override
	public RisenPartyChange findById(Integer risenpcDeptid) {
		// TODO Auto-generated method stub
		return dao.findById(risenpcDeptid);
	}

	@Override
	public void delete(Integer risenpcDeptid) {
		// TODO Auto-generated method stub
		dao.delete(risenpcDeptid);
	}

	@Override
	public RisenPartyChange update(RisenPartyChange bean) {
		// TODO Auto-generated method stub
		return dao.update(bean);
	}
	@Autowired
	private IRisenPartyChangeDao dao;
	@Autowired
	private CmsUserMng manager;
	@Autowired
	private CmsDepartmentMng cmsDeptMng;
	@Override
	public RisenPartyChange saveDevparty(RisenDevparty devParty) {
		// TODO Auto-generated method stub
		RisenPartyChange partyChange = new RisenPartyChange();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		partyChange.setRisenpcAddress(devParty.getRisendpElevenAddress());
		try {
			partyChange.setRisenpcBirthday(format.parse(devParty.getRisendpBirth()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		partyChange.setRisenpcCdate(new Date());
		partyChange.setRisenpcChangetype("1");
		Integer userId = new Integer(devParty.getRisendpExpands4());
		Integer deptId = new Integer(devParty.getRisendpExpands3());
		partyChange.setRisenpcCuserid(manager.findById(userId));
		partyChange.setRisenpcDeptid(cmsDeptMng.findById(deptId));
		partyChange.setRisenpcDeptname(cmsDeptMng.findById(deptId).getName());
		partyChange.setRisenpcEducation(devParty.getRisendpFiveEducation());
		partyChange.setRisenpcEmail(null);
		partyChange.setRisenpcIdnumber(devParty.getRisendpIdnumber());
		partyChange.setRisenpcMobile(null);
		partyChange.setRisenpcNational(devParty.getRisendpNation());
		partyChange.setRisenpcNative(devParty.getRisendpNative());
		partyChange.setRisenpcOriginaldeptid(null);
		partyChange.setRisenpcOtherphone(null);
		partyChange.setRisenpcPolicaltype("2");
		partyChange.setRisenpcSex(devParty.getRisendpSex());
		partyChange.setRisenpcUsername(devParty.getRisendpName());
		partyChange = dao.save(partyChange);
		return partyChange;
	}

	@Override
	public RisenPartyChange updeteDevparty(RisenPartyChange partyChange) {
		// TODO Auto-generated method stub
		return dao.updeteDevparty(partyChange);
	}
}
