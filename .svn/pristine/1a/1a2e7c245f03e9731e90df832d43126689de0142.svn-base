package com.risen.dao;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.risen.entity.RisenReject;

public interface IRisenRejectDao {
	public Pagination getPage(Integer departId,int pageNo,int pageSize,int status);
	//保存
	public RisenReject save(RisenReject reject);
	public RisenReject findById(Integer reject);
	//修改状态
	public Integer changeStatus(Integer departId,Integer contentId);
	//根据组织ID和文章ID找驳回列表
	public List<RisenReject> getListByDepartIdAndContentId(Integer departId,
			Integer contentId);
	//用于更改意见
	public Integer changeReason(Integer risenrjUnid, String reason);
	
}
