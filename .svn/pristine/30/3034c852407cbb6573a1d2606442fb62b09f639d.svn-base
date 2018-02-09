package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsAppealMail;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;

public interface CmsAppealMailDao {
	public Pagination getPage(Integer departId,int pageNo,int pageSize);
	public CmsAppealMail findById(Integer id);

	public CmsAppealMail save(CmsAppealMail bean);

	public int update(Integer appealId);

	public CmsAppealMail deleteById(Integer id);
	
	public boolean checkIsExist(String code);
	
	public CmsAppealMail fingByCode(String code);
}
