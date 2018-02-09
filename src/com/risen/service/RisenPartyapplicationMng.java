package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenPartyapplication;

public interface RisenPartyapplicationMng {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenPartyapplication findById(Integer id);

	public RisenPartyapplication save(RisenPartyapplication bean);

	public RisenPartyapplication update(RisenPartyapplication bean);

	public RisenPartyapplication deleteById(Integer id);
	
	public RisenPartyapplication[] deleteByIds(Integer[] ids);
}