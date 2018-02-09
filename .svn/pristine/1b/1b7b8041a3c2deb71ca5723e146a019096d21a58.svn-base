package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsBallot;
import com.jeecms.cms.entity.assist.CmsBallotItem;
import com.jeecms.common.page.Pagination;

public interface CmsBallotItemDao {
	public Pagination getPage(Integer ballotId,int pageNo,int pageSize); 
	public CmsBallotItem findById(Integer id);
	public CmsBallotItem save(CmsBallotItem bean);
	public CmsBallotItem update(CmsBallotItem bean);
	public CmsBallotItem deleteById(Integer id);
	//判断候选项是否已添加
	public boolean existBallotItem(String name,Integer ballotId);
	//删除指定活动下的所有子项
	public void deleteAllItem(Integer itemId);
}
