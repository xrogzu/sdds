package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenJoinorg;

public interface RisenJoinorgMng {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenJoinorg findById(Integer id);

	public RisenJoinorg save(RisenJoinorg bean);

	public RisenJoinorg update(RisenJoinorg bean);

	public RisenJoinorg deleteById(Integer id);
	
	public RisenJoinorg[] deleteByIds(Integer[] ids);
}