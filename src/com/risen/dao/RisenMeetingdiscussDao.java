package com.risen.dao;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenMeetingdiscuss;

public interface RisenMeetingdiscussDao {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenMeetingdiscuss findById(Integer id);

	public RisenMeetingdiscuss save(RisenMeetingdiscuss bean);

	public RisenMeetingdiscuss updateByUpdater(Updater<RisenMeetingdiscuss> updater);

	public RisenMeetingdiscuss deleteById(Integer id);
}