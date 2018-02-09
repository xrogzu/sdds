package com.risen.dao;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenPartyrecord;

public interface RisenPartyrecordDao {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenPartyrecord findById(Integer id);

	public RisenPartyrecord save(RisenPartyrecord bean);

	public RisenPartyrecord updateByUpdater(Updater<RisenPartyrecord> updater);

	public RisenPartyrecord deleteById(Integer id);
}