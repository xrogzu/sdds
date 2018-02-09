package com.risen.dao;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenPoliticalcheck;

public interface RisenPoliticalcheckDao {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenPoliticalcheck findById(Integer id);

	public RisenPoliticalcheck save(RisenPoliticalcheck bean);

	public RisenPoliticalcheck updateByUpdater(Updater<RisenPoliticalcheck> updater);

	public RisenPoliticalcheck deleteById(Integer id);
}