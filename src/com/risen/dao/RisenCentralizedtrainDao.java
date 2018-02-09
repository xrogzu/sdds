package com.risen.dao;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenCentralizedtrain;

public interface RisenCentralizedtrainDao {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenCentralizedtrain findById(Integer id);

	public RisenCentralizedtrain save(RisenCentralizedtrain bean);

	public RisenCentralizedtrain updateByUpdater(Updater<RisenCentralizedtrain> updater);

	public RisenCentralizedtrain deleteById(Integer id);
}