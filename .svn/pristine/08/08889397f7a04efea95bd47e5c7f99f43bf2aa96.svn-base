package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenQuota;

public interface IRisenQuotaService {
	//查询指定部门的指标
	public Pagination getPage(int pageNo, int pageSize,Integer deptId,String risenType);
	public Pagination getPage(int pageNo, int pageSize,Integer deptId,String risenType,Integer id);
	public RisenQuota save(RisenQuota quota);
	public RisenQuota findById(Integer id);
	public void delete(Integer id);
	public RisenQuota update(RisenQuota bean);
	//判断某指标是否已添加
	public boolean existQuota(String quotaName,Integer departId,String risenType,String risenAddType);
	
	
}
