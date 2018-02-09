package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenMaterialfiling;

public interface RisenMaterialfilingMng {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenMaterialfiling findById(Integer id);

	public RisenMaterialfiling save(RisenMaterialfiling bean);

	public RisenMaterialfiling update(RisenMaterialfiling bean);

	public RisenMaterialfiling deleteById(Integer id);
	
	public RisenMaterialfiling[] deleteByIds(Integer[] ids);
}