package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenQaires;

public interface IRisenQairesService {
	public Pagination getPage(String startTime,String endTime,Integer pageNo, Integer pageSize,Boolean disable,Integer status);
	public RisenQaires save(RisenQaires qaires);
	public void delete(Integer id);
	public RisenQaires findById(Integer id);
	public RisenQaires update(RisenQaires qaires);
	public Pagination findList(Integer pageNo, Integer pageSize,Integer status);
}
