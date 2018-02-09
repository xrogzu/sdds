package com.risen.dao;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenQaires;

public interface IRisenQairesDao {
	public Pagination getPage(String startTime,String endTime,Integer pageNo, Integer pageSize,Boolean disable,
			Integer status);
	public RisenQaires save(RisenQaires bean);
	public RisenQaires findById(Integer id);
	public void delete(Integer id);
	public RisenQaires update(RisenQaires bean);
	public Pagination findList(Integer pageNo, Integer pageSize,Integer status);
}
