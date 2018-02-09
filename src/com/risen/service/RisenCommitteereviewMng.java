package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenCommitteereview;

public interface RisenCommitteereviewMng {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenCommitteereview findById(Integer id);

	public RisenCommitteereview save(RisenCommitteereview bean);

	public RisenCommitteereview update(RisenCommitteereview bean);

	public RisenCommitteereview deleteById(Integer id);
	
	public RisenCommitteereview[] deleteByIds(Integer[] ids);
}