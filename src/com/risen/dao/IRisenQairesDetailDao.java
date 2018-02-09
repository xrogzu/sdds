package com.risen.dao;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenQairesDetail;

public interface IRisenQairesDetailDao {
	public Pagination getPage(Integer recordId,Integer pageNo,Integer pageSize);
	public RisenQairesDetail save(RisenQairesDetail bean);
	public void deleteByRecord(Integer recordId);
	public void deleteByTopic(Integer topicId);
	public RisenQairesDetail findById(Integer id);
}
