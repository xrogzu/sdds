package com.risen.dao;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenPartyapplication;

public interface RisenPartyapplicationDao {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenPartyapplication findById(Integer id);

	public RisenPartyapplication save(RisenPartyapplication bean);

	public RisenPartyapplication updateByUpdater(Updater<RisenPartyapplication> updater);

	public RisenPartyapplication deleteById(Integer id);
}