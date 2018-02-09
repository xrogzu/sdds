package com.jeecms.cms.manager.assist;

import com.risen.entity.CmsAppealMailReplay;


public interface CmsAppealMailReplayMng {
	public CmsAppealMailReplay findById(Integer id);
	public CmsAppealMailReplay save(CmsAppealMailReplay bean);
}
