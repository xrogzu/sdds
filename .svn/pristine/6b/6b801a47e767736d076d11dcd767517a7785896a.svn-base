package com.risen.service;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.risen.entity.RisenQairesRecord;

public interface IRisenQairesRecordService {
	public RisenQairesRecord save(RisenQairesRecord record);
	public void delete(Integer id);
	public RisenQairesRecord findById(Integer id);
	public RisenQairesRecord update(RisenQairesRecord record);
	public Pagination getPage(Integer qairesId,String userName,String startDate,String endDate,Integer pageNo,Integer pageSize);
	public List<RisenQairesRecord> getList(Integer qairesId,String userName,String startDate,String endDate);
	public List<RisenQairesRecord> findList(Integer qairesId,CmsUser user);
	public void deleteByQaires(Integer qaireId);
	public void deleteByUser(Integer userId);
}
