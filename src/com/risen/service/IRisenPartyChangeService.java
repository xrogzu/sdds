package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenDevparty;
import com.risen.entity.RisenPartyChange;


public interface IRisenPartyChangeService {
	public RisenPartyChange save(RisenPartyChange bean);
	public Pagination getAllInfoByDepartId(int pageNo, int pageSize,String risenpcDeptid,String changeType);
	public RisenPartyChange findById(Integer risenpcDeptid);
	public void delete(Integer risenpcDeptid);
	public RisenPartyChange update(RisenPartyChange bean);
	
	//保存发展党员
	public RisenPartyChange saveDevparty(RisenDevparty devParty);
	//修改发展党员信息
	public RisenPartyChange updeteDevparty(RisenPartyChange partyChange);
}
