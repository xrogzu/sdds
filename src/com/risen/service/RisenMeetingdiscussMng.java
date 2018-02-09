package com.risen.service;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenMeetingdiscuss;

public interface RisenMeetingdiscussMng {
	public Pagination getPage(int pageNo, int pageSize);

	public RisenMeetingdiscuss findById(Integer id);

	public RisenMeetingdiscuss save(RisenMeetingdiscuss bean);

	public RisenMeetingdiscuss update(RisenMeetingdiscuss bean);

	public RisenMeetingdiscuss deleteById(Integer id);
	
	public RisenMeetingdiscuss[] deleteByIds(Integer[] ids);
}