package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenPoliticalcheck;

public interface RisenPoliticalcheckMng {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenPoliticalcheck findById(Integer id);

	public RisenPoliticalcheck save(RisenPoliticalcheck bean);

	public RisenPoliticalcheck update(RisenPoliticalcheck bean);

	public RisenPoliticalcheck deleteById(Integer id);
	
	public RisenPoliticalcheck[] deleteByIds(Integer[] ids);
}