package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsBallotItem;
import com.jeecms.cms.entity.assist.CmsBallotRecord;
import com.jeecms.common.page.Pagination;

public interface CmsBallotRecordDao {
	public Pagination getPage(Integer itemId,int pageNo,int pageSize); 
	public CmsBallotRecord findById(Integer id);
	public CmsBallotRecord save(CmsBallotRecord bean);
	public CmsBallotRecord update(CmsBallotRecord bean);
	public CmsBallotRecord deleteById(Integer id);
	//一个IP地址只能投一个标题
	public boolean existGive(String ip,String itemId);
	//删除指定BallotItemId的所有记录
	public void deleteByItemId(Integer id);
}
