package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenQairesDetail;

public interface IRisenQairesDetailService {
	public RisenQairesDetail save(RisenQairesDetail detail);
	public Pagination getPage(Integer recordId,Integer pageNo,Integer pageSize);
	public void deleteByTopic(Integer topicId);
	public RisenQairesDetail findById(Integer id);
	public void deleteByRecord(Integer recordId);
}
