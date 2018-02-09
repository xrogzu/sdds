package com.risen.service;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.CmsAppealMail;

public interface CmsAppealMailMng {
	public Pagination getPage(int pageNo,int pageSize,Integer departId);
	public CmsAppealMail getReplay(String code);
	public CmsAppealMail findById(Integer appealId);
	public CmsAppealMail save(CmsAppealMail bean);
	public CmsAppealMail update(CmsAppealMail bean);
	public CmsAppealMail deleteById(Integer ids);
	public CmsAppealMail[] deleteByIds(Integer[] ids);
	public List<CmsAppealMail> getList(Integer appealId,String name,String title,String content,String code);

}
