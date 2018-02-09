package com.risen.dao;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenEducationcheck;

public interface RisenEducationcheckDao {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenEducationcheck findById(Integer id);

	public RisenEducationcheck save(RisenEducationcheck bean);

	public RisenEducationcheck updateByUpdater(Updater<RisenEducationcheck> updater);

	public RisenEducationcheck deleteById(Integer id);
}