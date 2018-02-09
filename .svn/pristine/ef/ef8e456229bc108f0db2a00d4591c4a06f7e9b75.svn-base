package com.risen.dao;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenPartyChange;

public interface IRisenPartyChangeDao {
	public RisenPartyChange save(RisenPartyChange bean);
	public Pagination getAllInfoByDepartId(int pageNo, int pageSize,String risenpcDeptid,String changeType);
	public RisenPartyChange findById(Integer risenpcDeptid);
	public void delete(Integer risenpcDeptid);
	public RisenPartyChange update(RisenPartyChange bean);
	
	//修改发展党员信息
	public RisenPartyChange updeteDevparty(RisenPartyChange partyChange);
}
