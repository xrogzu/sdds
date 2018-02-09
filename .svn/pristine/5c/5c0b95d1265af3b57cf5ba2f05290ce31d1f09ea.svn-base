package com.risen.dao;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenQuota;

public interface IRisenQuotaDao {
	//查询指定部门的指标
	public Pagination getPage(int pageNo, int pageSize,Integer deptId,String risenType);
	public Pagination getPage(int pageNo, int pageSize,Integer deptId,String risenType,Integer id);
	public RisenQuota save(RisenQuota quota);
	public RisenQuota findById(Integer id);
	public void delete(Integer id);
	public RisenQuota update(RisenQuota bean);
	//验证是否存在此指标
	public boolean existQuota(String quotaName,Integer departId,String risenType,String risenAddType);
	public RisenQuota score(Integer id);
}
