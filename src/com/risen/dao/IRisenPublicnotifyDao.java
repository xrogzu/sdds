package com.risen.dao;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenPublicnotify;

public interface IRisenPublicnotifyDao {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenPublicnotify findById(Integer id);

	public RisenPublicnotify save(RisenPublicnotify bean);

	public RisenPublicnotify update(RisenPublicnotify bean) ;

	public RisenPublicnotify deleteById(Integer id);
}