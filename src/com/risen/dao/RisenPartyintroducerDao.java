package com.risen.dao;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenPartyintroducer;

public interface RisenPartyintroducerDao {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenPartyintroducer findById(Integer id);

	public RisenPartyintroducer save(RisenPartyintroducer bean);

	public RisenPartyintroducer updateByUpdater(Updater<RisenPartyintroducer> updater);

	public RisenPartyintroducer deleteById(Integer id);
}