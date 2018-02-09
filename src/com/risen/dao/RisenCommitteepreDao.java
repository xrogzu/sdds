package com.risen.dao;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenCommitteepre;

public interface RisenCommitteepreDao {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenCommitteepre findById(Integer id);

	public RisenCommitteepre save(RisenCommitteepre bean);

	public RisenCommitteepre updateByUpdater(Updater<RisenCommitteepre> updater);

	public RisenCommitteepre deleteById(Integer id);
}