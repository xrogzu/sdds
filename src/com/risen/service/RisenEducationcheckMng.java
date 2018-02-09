package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenEducationcheck;

public interface RisenEducationcheckMng {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenEducationcheck findById(Integer id);

	public RisenEducationcheck save(RisenEducationcheck bean);

	public RisenEducationcheck update(RisenEducationcheck bean);

	public RisenEducationcheck deleteById(Integer id);
	
	public RisenEducationcheck[] deleteByIds(Integer[] ids);
}