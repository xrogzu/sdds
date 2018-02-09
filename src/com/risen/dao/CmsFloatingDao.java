package com.risen.dao;

import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsDepartment;
import com.risen.entity.CmsFloating;

public interface CmsFloatingDao {
	public Pagination getPage(int pageNo, int pageSize);

	public CmsFloating findById(Integer sddsfiId);

	public CmsFloating save(CmsFloating bean);

	public CmsFloating updateByUpdater(Updater<CmsFloating> updater);

	public CmsFloating deleteById(Integer sddsfiId);
	
	public List<CmsFloating> getByUserId(Integer userId);
	
	public List<CmsFloating> getUserByType(String type);
	
	public void updateFloating(Integer userId,String changeType,CmsDepartment depart);
}