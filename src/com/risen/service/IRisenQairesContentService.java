package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenQairesContent;

public interface IRisenQairesContentService {
	public RisenQairesContent save(RisenQairesContent content);
	public Pagination getPage(Integer topicId,Integer pageNo, Integer pageSize);
	public void deleteByRecord(Integer recordId);
}
