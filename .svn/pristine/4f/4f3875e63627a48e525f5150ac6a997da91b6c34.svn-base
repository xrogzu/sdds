package com.risen.dao;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.risen.entity.RisenQairesRecord;

public interface IRisenQairesRecordDao {
	public Pagination getPage(Integer qairesId,String userName,String startDate,String endDate,Integer pageNo,Integer pageSize);
	public List<RisenQairesRecord> getList(Integer qairesId,String userName,String startDate,String endDate);
	public List<RisenQairesRecord> findList(Integer qairesId,CmsUser user);
	public void deleteByQaires(Integer qairesId);
	public void deleteByUser(Integer userId);
	public RisenQairesRecord save(RisenQairesRecord bean);
	public RisenQairesRecord findById(Integer id);
	public void delete(Integer id);
	public RisenQairesRecord update(RisenQairesRecord bean);
}
