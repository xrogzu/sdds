package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenActive;

public interface RisenActiveMng {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenActive findById(Integer id);

	public RisenActive save(RisenActive bean);

	public RisenActive update(RisenActive bean);

	public RisenActive deleteById(Integer id);
	
	public RisenActive[] deleteByIds(Integer[] ids);
}