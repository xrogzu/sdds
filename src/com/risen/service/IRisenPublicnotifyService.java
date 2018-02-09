package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenPublicnotify;

public interface IRisenPublicnotifyService {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenPublicnotify findById(Integer id);

	public RisenPublicnotify save(RisenPublicnotify bean);

	public RisenPublicnotify update(RisenPublicnotify bean);

	public RisenPublicnotify deleteById(Integer id);
	
	public RisenPublicnotify[] deleteByIds(Integer[] ids);
}