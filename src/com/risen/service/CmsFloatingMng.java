package com.risen.service;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsDepartment;
import com.jeecms.core.entity.CmsUser;
import com.risen.entity.CmsFloating;

public interface CmsFloatingMng {
	public Pagination getPage(int pageNo, int pageSize);

	public CmsFloating findById(Integer sddsfiId);

	public CmsFloating save(CmsFloating bean);

	public CmsFloating update(CmsFloating bean);

	public CmsFloating deleteById(Integer sddsfiId);
	
	public CmsFloating[] deleteByIds(Integer[] ids);
	//添加转入转出关系
	public void outAndaddWithUser(CmsUser user,boolean isOutCity,CmsDepartment olddepart,CmsDepartment newdepart,String sddscpChanges,String zclx);
	//添加转入转出关系
	public void updateWithUser(Integer userId,String changeType,CmsDepartment depart);
	//通过用户来得到转入转出关系
	public List<CmsFloating> getByUserId(Integer userId);
	//添加两条记录
	public void saveOutWithUser(CmsUser user,String type,String outType);
}