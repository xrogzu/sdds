package com.risen.dao;

import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.risen.entity.CmsAppealMail;

public interface ICmsAppealMailDao {
	public Pagination getPage(int pageNo,int pageSize,Integer departId);
	public CmsAppealMail findById(Integer appealId);

	public CmsAppealMail save(CmsAppealMail bean);

	public CmsAppealMail update(CmsAppealMail bean);

	public CmsAppealMail deleteById(Integer id);
	
	public List<CmsAppealMail> getList(Integer appealId,String name,String code);
}
