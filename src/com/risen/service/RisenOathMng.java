package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenOath;

public interface RisenOathMng {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenOath findById(Integer id);

	public RisenOath save(RisenOath bean);

	public RisenOath update(RisenOath bean);

	public RisenOath deleteById(Integer id);
	
	public RisenOath[] deleteByIds(Integer[] ids);
}